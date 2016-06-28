package com.lmh.function.pattern.abstractfactory;

import com.lmh.function.pattern.Man;
import com.lmh.function.pattern.People;

public class ManFactory implements Factory {

	@Override
	public People product() {
		return new Man();
	}

}
