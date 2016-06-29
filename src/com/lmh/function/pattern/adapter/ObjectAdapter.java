package com.lmh.function.pattern.adapter;

public class ObjectAdapter implements Targetable {
	
	public Source source;
	
	public ObjectAdapter(Source source) {
		this.source = source;
	}

	@Override
	public void method2() {
		System.err.println("this is ObjectAdapter method2");
	}

	@Override
	public void method1() {
		source.method1();
	}

}
