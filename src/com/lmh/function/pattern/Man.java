package com.lmh.function.pattern;

public class Man implements People {

	@Override
	public void eat() {
		System.err.println("Man eating!");
	}

	@Override
	public void sleep() {
		System.err.println("Man sleeping!");
	}

}
