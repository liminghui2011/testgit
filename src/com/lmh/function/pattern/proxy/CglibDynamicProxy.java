package com.lmh.function.pattern.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibDynamicProxy implements MethodInterceptor {
	
    /**
     * 生成代理类工厂
     * @author com.tiantian
     * @param realObj
     * @return 返回生成的代理类
     */
    public Object getProxyInstance(Object realObj){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(realObj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

	private void before(){
		System.err.println("proxy before");
	}
	
	private void after(){
		System.err.println("proxy after");
	}

	@Override
	public Object intercept(Object object, Method method, Object[] params, MethodProxy proxy) throws Throwable {
		before();
		Object result = proxy.invokeSuper(object, params);
		after();
		return result;
	}
}
