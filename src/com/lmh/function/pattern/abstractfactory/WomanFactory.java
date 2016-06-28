package com.lmh.function.pattern.abstractfactory;

import com.lmh.function.pattern.People;
import com.lmh.function.pattern.Woman;

public class WomanFactory implements Factory {

	@Override
	public People product() {
		return new Woman();
	}

}
