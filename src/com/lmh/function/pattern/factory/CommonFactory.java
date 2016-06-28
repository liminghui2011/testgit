package com.lmh.function.pattern.factory;

import com.lmh.function.pattern.Man;
import com.lmh.function.pattern.People;
import com.lmh.function.pattern.Woman;

/**
 * 普通工厂方法
 */
public class CommonFactory {

	public People getPeople(String peopleType){
		if ("MAN".equals(peopleType)) {
			return new Man();
		}else if("WOMAN".equals(peopleType)){
			return new Woman();
		}else{
			System.err.println("there is no type of "+peopleType+" can create");
		}
		return null;
	}
	
}
