package com.lmh.function.pattern;

public class Woman implements People {

	@Override
	public void eat() {
		System.err.println("Woman eating!");
	}

	@Override
	public void sleep() {
		System.err.println("Woman sleeping!");
	}

}
