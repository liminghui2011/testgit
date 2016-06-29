package com.lmh.function.pattern.adapter;

public abstract class AbstractSource implements Targetable {

	public void method1() {
		System.err.println("this is AbstractSource method1");
	}

	public void method2() {
		System.err.println("this is AbstractSource method2");
	}

}
