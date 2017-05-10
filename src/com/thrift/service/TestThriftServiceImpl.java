package com.thrift.service;

import org.apache.thrift.TException;

import com.thrift.datatype.ResultInt;
import com.thrift.datatype.ResultStr;
import com.thrift.datatype.ThriftResult;

public class TestThriftServiceImpl implements TestThriftService.Iface{

	@Override
	public ResultStr getStr(String srcStr1, String srcStr2) throws TException {
		long startTime = System.currentTimeMillis();  
        String res = srcStr1 + srcStr2;   
        long stopTime = System.currentTimeMillis();  
        ResultStr resultStr = new ResultStr(ThriftResult.SUCCESS, res);  
        System.out.println("[getStr]time interval: " + (stopTime-startTime));  
        return resultStr;
	}

	@Override
	public ResultInt getInt(int val) throws TException {
		long startTime = System.currentTimeMillis();  
        val = val * 10;   
        long stopTime = System.currentTimeMillis();  
        ResultInt resultInt = new ResultInt(ThriftResult.SUCCESS, val);  
        System.out.println("[getStr]time interval: " + (stopTime-startTime));  
        return resultInt;
	}
	
	
}
