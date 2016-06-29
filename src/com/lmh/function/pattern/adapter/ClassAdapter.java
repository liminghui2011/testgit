package com.lmh.function.pattern.adapter;

public class ClassAdapter extends Source implements Targetable {

	@Override
	public void method2() {
		System.err.println("this is classadapter method2");
	}

}
