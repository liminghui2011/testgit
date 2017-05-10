package com.thrift.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import org.apache.thrift.TProcessor;  
import org.apache.thrift.protocol.TBinaryProtocol;  
import org.apache.thrift.protocol.TBinaryProtocol.Factory;  
import org.apache.thrift.server.TServer;  
import org.apache.thrift.server.TSimpleServer;  
import org.apache.thrift.server.TThreadPoolServer;  
import org.apache.thrift.server.TThreadPoolServer.Args;  
import org.apache.thrift.transport.TServerSocket;  
import org.apache.thrift.transport.TServerTransport;  
import org.apache.thrift.transport.TTransportException; 

public class ThriftServiceMain
{

	private static int m_thriftPort = 12356;
	private static TestThriftServiceImpl m_myService = new TestThriftServiceImpl();
	private static TServer m_server = null;

	private static void createNonblockingServer() throws TTransportException
	{
		TProcessor tProcessor = new TestThriftService.Processor<TestThriftService.Iface>(
				m_myService);
		TNonblockingServerSocket nioSocket = new TNonblockingServerSocket(
				m_thriftPort);
		TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(nioSocket);
		tnbArgs.processor(tProcessor);
		tnbArgs.transportFactory(new TFramedTransport.Factory());
		tnbArgs.protocolFactory(new TBinaryProtocol.Factory());
		
		// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
		m_server = new TNonblockingServer(tnbArgs);
	}

	public static boolean start()
	{
		try
		{
			createNonblockingServer();
		}
		catch (TTransportException e)
		{
			System.out.println("start server error!" + e);
			return false;
		}
		System.out.println("service at port: " + m_thriftPort);
		m_server.serve();
		return true;
	}

	public static void main(String[] args)
	{
//		if (!start())
//		{
//			System.exit(0);
//		}
		try{  
			// 设置服务器端口为7911
			TServerSocket serverTransport = new TServerSocket(7911);
			// 设置协议工厂为TBinaryProtocol.Factory
			Factory proFactory = new TBinaryProtocol.Factory();
			// 关联处理器与Hello服务的实现
			TProcessor processor = new TestThriftService.Processor<TestThriftService.Iface>(
					new TestThriftServiceImpl());
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(processor);
			tArgs.protocolFactory(proFactory);
			// 使用TSimpleServer
			TServer server = new TSimpleServer(tArgs);
			System.out.println("Start server on port 7911....");
			server.serve();
		}
		catch (TTransportException e)
		{
			e.printStackTrace();
		}
	}

}
