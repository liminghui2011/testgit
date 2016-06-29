package com.lmh.function.pattern.proxy;

public class SourceClass implements TargetInterface {

	@Override
	public void method() {
		System.err.println("source method");
	}

}
