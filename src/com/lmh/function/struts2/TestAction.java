package com.lmh.function.struts2;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6456427851805930569L;

	@Override
	public String execute() throws Exception {
		System.err.println("execute running!");
		return super.execute();
	}
	
	
}
