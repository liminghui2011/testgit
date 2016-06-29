package com.lmh.function.pattern.proxy;

public class TargetProxy implements TargetInterface {
	
	public SourceClass source;
	
	public TargetProxy() {
		source = new SourceClass();
	}
	
	public TargetProxy(SourceClass source){
		this.source = source;
	}

	@Override
	public void method() {
		before();
		source.method();
		after();
	}

	private void before(){
		System.err.println("proxy before");
	}
	
	private void after(){
		System.err.println("proxy after");
	}
}
