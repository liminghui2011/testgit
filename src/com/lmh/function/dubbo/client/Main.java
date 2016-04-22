package com.lmh.function.dubbo.client;

public class Main {

    public static void main(String[] args) throws InterruptedException {
    	int i=0;
    	while(i++<2){
    		ChatAction act = new ChatAction();
    		act.SayHello();
    		Thread.sleep(30000);
    	}
    }

}
