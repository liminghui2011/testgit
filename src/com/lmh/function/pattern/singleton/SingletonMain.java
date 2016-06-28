package com.lmh.function.pattern.singleton;

public class SingletonMain extends Base {

	public static void main(String[] args) {
		SingletonLazy singletonLazy = SingletonLazy.getInstance();
		System.err.println("第一次：" + singletonLazy.getFlag());

		SingletonLazy singletonLazy2 = SingletonLazy.getInstance();
		singletonLazy2.setFlag("second,have change");
		System.err.println("第二次：" + singletonLazy2.getFlag());

		Base b = new Base();
		Base c = new SingletonMain();
		b.a();
		b.b();
		SingletonMain.a();
		c.b();
	}

	static void a() {
		System.out.println("C");
	}

	void b() {
		System.out.println("D");
	}
}

class Base {
	static void a() {
		System.out.println("A");
	}

	void b() {
		System.out.println("B");
	}
}
