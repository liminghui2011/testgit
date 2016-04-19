package com.lmh.function.springmvc.controller;

import com.lmh.function.springmvc.annotation.Controller;
import com.lmh.function.springmvc.annotation.RequestMapping;
import com.lmh.function.springmvc.entity.View;

@Controller
public class TestController2 {
	//使用RequestMapping注解指明forward1方法的访问路径  
	@RequestMapping("LoginUI2/Login2")
	public View forward1(){
	    //执行完forward1方法之后返回的视图
	    return new View("/login2.jsp");  
	}

	//使用RequestMapping注解指明forward2方法的访问路径  
	@RequestMapping("LoginUI2/Login3")
	public View forward2(){
	    //执行完forward2方法之后返回的视图
	    return new View("/login3.jsp");  
	}
}
