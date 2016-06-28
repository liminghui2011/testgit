package com.lmh.function.pattern.singleton;

/**
 * 懒汉式实现单例模式
 * @Title: SingletonLazy.java 
 * @Package com.lmh.function.pattern.singleton 
 * @Description: 
 * @author liminghui   
 * @date 2016年6月28日 上午9:38:17 
 * @version V1.0
 */
public class SingletonLazy {
	
	//为什么定义为静态变量？
	private static SingletonLazy instance = null;
	
	private SingletonLazy(){
		
	}
	
	/**
	 * 通过静态方法的方式来创建并获取实例
	 * 如果考虑线程并发，需要加上同步操作，可以是同步方法或同步代码块。如下下面的getInstance2和getInstance3
	 * @return
	 */
	public static SingletonLazy getInstance(){
		if (instance == null) {
			instance = new SingletonLazy();
		}
		return instance;
	}
	
	/*
	private static synchronized SingletonLazy getInstance2(){
		if (instance == null) {
			instance = new SingletonLazy();
		}
		return instance;
	}
	
	private static SingletonLazy getInstance3(){
		synchronized(SingletonLazy.class){
			if (instance == null) {
				instance = new SingletonLazy();
			}
		}
		return instance;
	}
	*/
	
	//测试当处于单例模式下，某个线程修改了单例对象里面的内容后，另一个线程再获取时该值是否会变
	private String flag = "first,nochange";

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
