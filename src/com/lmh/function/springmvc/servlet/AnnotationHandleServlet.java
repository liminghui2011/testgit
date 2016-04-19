package com.lmh.function.springmvc.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmh.function.springmvc.annotation.Controller;
import com.lmh.function.springmvc.annotation.RequestMapping;
import com.lmh.function.springmvc.entity.RequestMap;
import com.lmh.function.springmvc.entity.View;
import com.lmh.function.springmvc.util.BeanUtils;
import com.lmh.function.springmvc.util.ScanClassUtil;
import com.lmh.function.springmvc.util.WebContext;

public class AnnotationHandleServlet extends HttpServlet{
	
	public static String FORWARD = "forward";//服务器跳转

	public static String REDIRECT = "redirect";//客户端跳转

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 解析请求地址中包含的指定后缀格式的部分地址
	 * @param request
	 * @return
	 */
	private String pareRequestURI(HttpServletRequest request){
        String path = request.getContextPath()+"/";
        String requestUri = request.getRequestURI();
        String midUrl = requestUri.replaceFirst(path, "");
        
        //不一定仅使用.来截取
        String lasturl = midUrl.substring(0, midUrl.lastIndexOf("."));
        return lasturl;
    }
	
	@Override
    public void init(ServletConfig config) throws ServletException {
        /**
         * 重写了Servlet的init方法后一定要记得调用父类的init方法，
         * 否则在service/doGet/doPost方法中使用getServletContext()方法获取ServletContext对象时
         * 就会出现java.lang.NullPointerException异常
         */
        super.init(config); 
        System.out.println("---初始化开始---");
        //获取web.xml中配置的要扫描的包
        String basePackage = config.getInitParameter("basePackage");
        //如果配置了多个包，例如：<param-value>me.gacl.web.controller,me.gacl.web.UI</param-value>
        if (basePackage.indexOf(",")>0) {
            //按逗号进行分隔
            String[] packageNameArr = basePackage.split(",");
            for (String packageName : packageNameArr) {
                initRequestMapingMap(packageName);
            }
        }else {
            initRequestMapingMap(basePackage);
        }
        System.out.println("----初始化结束---");
    }
	
	/**
	* @Method: initRequestMapingMap
	* @Description:添加使用了Controller注解的Class到RequestMapingMap中
	* @Anthor:孤傲苍狼
	* @param packageName
	*/ 
	private void initRequestMapingMap(String packageName){
	    Set<Class<?>> setClasses =  ScanClassUtil.getClasses(packageName);
	    for (Class<?> clazz :setClasses) {
	        if (clazz.isAnnotationPresent(Controller.class)) {
	        	String parentPath = clazz.getAnnotation(Controller.class).name();
	            Method [] methods = BeanUtils.findDeclaredMethods(clazz);
	            //循环方法，找匹配的方法进行执行
	            for(Method m:methods){
	                if(m.isAnnotationPresent(RequestMapping.class)){
	                    String anoPath = parentPath+m.getAnnotation(RequestMapping.class).value();
	                    if(anoPath!=null && !"".equals(anoPath.trim())){
	                        if (RequestMap.getRequesetMap().containsKey(anoPath)) {
	                            throw new RuntimeException("RequestMapping映射的地址不允许重复！");
	                        }
	                        RequestMap.put(anoPath, clazz);
	                    }
	                }
	            }
	        }
	    }
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.service(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.service(request, response);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//将当前线程中HttpServletRequest对象存储到ThreadLocal中，以便在Controller类中使用
		WebContext.requestHodler.set(request);
		
		//将当前线程中HttpServletResponse对象存储到ThreadLocal中，以便在Controller类中使用
		WebContext.responseHodler.set(response);
		
		//解析url
		String lasturl = pareRequestURI(request);
		
		//获取要使用的类
		String urlPath = null;
		Class<?> clazz = RequestMap.getRequesetMap().get(lasturl);
		if(clazz != null && clazz.isAnnotationPresent(Controller.class)){
			urlPath = clazz.getAnnotation(Controller.class).name();
		}
		
		//创建类的实例
		Object classInstance = BeanUtils.instanceClass(clazz);
		
		
		//获取类中定义的方法
		Method [] methods = BeanUtils.findDeclaredMethods(clazz);
		Method method = null;
		for(Method m:methods){//循环方法，找匹配的方法进行执行
		    if(m.isAnnotationPresent(RequestMapping.class)){
		    	String subPath = urlPath == null ? m.getAnnotation(RequestMapping.class).value() : urlPath.concat(m.getAnnotation(RequestMapping.class).value());
		        System.err.println("urlPath="+urlPath);
		    	if(subPath!=null && !subPath.trim().isEmpty() && lasturl.equals(subPath.trim())){
		            //找到要执行的目标方法
		            method = m;
		            break;
		        }
		    }
		}
		try {
		    if(method!=null){
		        //执行目标方法处理用户请求
		        Object retObject = method.invoke(classInstance);
		        //如果方法有返回值，那么就表示用户需要返回视图
		        if (retObject!=null) {
		            View view = (View)retObject;
		            System.err.println(view.toString());
		            //判断要使用的跳转方式
		            if(view.getDispathAction().equals(FORWARD)){
		                //使用服务器端跳转方式
		                request.getRequestDispatcher(view.getUrl()).forward(request, response);
		            }else if(view.getDispathAction().equals(REDIRECT)){
		                //使用客户端跳转方式
		                response.sendRedirect(request.getContextPath()+view.getUrl());
		            }else{
		                request.getRequestDispatcher(view.getUrl()).forward(request, response);
		            }
		        }
		    }
		} catch (IllegalArgumentException e) {
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		} catch (InvocationTargetException e) {
		    e.printStackTrace();
		}
	}
	
	
}
