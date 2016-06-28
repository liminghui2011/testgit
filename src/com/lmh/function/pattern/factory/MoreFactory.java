package com.lmh.function.pattern.factory;

import com.lmh.function.pattern.Man;
import com.lmh.function.pattern.People;
import com.lmh.function.pattern.Woman;

/**
 * 多工厂方法
 */
public class MoreFactory {

	public People getWoman(){
		return new Woman();
	}
	
	public People getMan(){
		return new Man();
	}
	
}
