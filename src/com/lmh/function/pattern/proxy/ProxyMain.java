package com.lmh.function.pattern.proxy;

public class ProxyMain {

	public static void main(String[] args) {
		TargetProxy proxy = new TargetProxy();
		//proxy.method();
		
		proxy = new TargetProxy(new SourceClass());
		//proxy.method();
		
		TargetInterface interface1 = (TargetInterface) DynamicProxy.getProxyInstanceFactory(new SourceClass());
		//interface1.method();
		
		CglibDynamicProxy cglibDynamicProxy = new CglibDynamicProxy();
		SourceClass sourceClass = (SourceClass) cglibDynamicProxy.getProxyInstance(new SourceClass());
		sourceClass.method();
	}

}
