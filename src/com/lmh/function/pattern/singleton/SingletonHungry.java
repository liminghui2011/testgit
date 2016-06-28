package com.lmh.function.pattern.singleton;

/**
 * 饿汉式实现单例模式
 * @Title: SingletonLazy.java 
 * @Package com.lmh.function.pattern.singleton 
 * @Description: 
 * @author liminghui   
 * @date 2016年6月28日 上午9:38:17 
 * @version V1.0
 */
public class SingletonHungry {
	
	private static SingletonHungry instance = new SingletonHungry();
	
	private SingletonHungry(){
		
	}
	
	/**
	 * 通过静态方法的方式来创建并获取实例，此方式则不存在多线程时，有可能创建不止一个实例的情况。
	 * @return
	 */
	public static SingletonHungry getInstance(){
		return instance;
	}
	
}
