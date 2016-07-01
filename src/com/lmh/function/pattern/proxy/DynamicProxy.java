package com.lmh.function.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于JDK实现的动态代理，被代理类必须实现至少一个接口，否则无法实现代理。
 * @Title: DynamicProxy.java 
 * @Package com.lmh.function.pattern.proxy 
 * @Description: 
 * @author liminghui   
 * @version V1.0
 */
public class DynamicProxy implements InvocationHandler {
	
	private Object obj;
    
    private DynamicProxy(Object obj) {
        this.obj = obj;
    }
    
    /**
     * 生成代理类工厂
     * @author com.tiantian
     * @param realObj
     * @return 返回生成的代理类
     */
    public static Object getProxyInstanceFactory(Object realObj){
        Class<?> classType = realObj.getClass();
        return Proxy.newProxyInstance(classType.getClassLoader(), 
                classType.getInterfaces(), new DynamicProxy(realObj));
    }

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(obj, args);
		after();
		return result;
	}

	private void before(){
		System.err.println("proxy before");
	}
	
	private void after(){
		System.err.println("proxy after");
	}
}
