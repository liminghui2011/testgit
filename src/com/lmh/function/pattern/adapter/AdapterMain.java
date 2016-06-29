package com.lmh.function.pattern.adapter;

public class AdapterMain {

	public static void main(String[] args) {
		Targetable classTarget = new ClassAdapter();
		classTarget.method1();
		classTarget.method2();
		
		Targetable objectTarget = new ObjectAdapter(new Source());
		objectTarget.method1();
		objectTarget.method2();
		
		AbstractSource source = new SourceSub1();
		source.method1();
		source.method2();
		
		source = new SourceSub2();
		source.method1();
		source.method2();
	}

}
