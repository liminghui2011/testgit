package com.lmh.function.springmvc.entity;

import javax.servlet.http.HttpServletRequest;

import com.lmh.function.springmvc.util.WebContext;

public class ViewData {
	private HttpServletRequest request;

	public ViewData() {
	    initRequest();
	}

	private void initRequest(){
	    //从requestHodler中获取request对象
	    this.request = WebContext.requestHodler.get();
	}

	public void put(String name,Object value){
	    this.request.setAttribute(name, value);
	}
}
