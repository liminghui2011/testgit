package com.thrift.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;

public class ThriftServiceMain
{

	private static int SERVER_PORT = 7911;

	public static void main(String[] args)
	{

//		singleThreadModel();
//		threadPoolModel();
//		nonblockIOModel();
//		halfAsyncAndSyncModel();
		asyncModel();

	}

	/**
	 * 单线程服务端模型
	 */
	public static void singleThreadModel()
	{
		try
		{
			System.out.println("HelloWorld TSimpleServer start ....");

			TProcessor tprocessor = new TestThriftService.Processor<TestThriftService.Iface>(
					new TestThriftServiceImpl());

			// 简单的单线程服务模型，一般用于测试
			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(tprocessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			// tArgs.protocolFactory(new TCompactProtocol.Factory());
			// tArgs.protocolFactory(new TJSONProtocol.Factory());
			TServer server = new TSimpleServer(tArgs);
			server.serve();

		}
		catch (Exception e)
		{
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}

	/**
	 * 线程池服务端模型
	 */
	public static void threadPoolModel()
	{
		try
		{
			System.out.println("HelloWorld TThreadPoolServer start ....");

			TProcessor tprocessor = new TestThriftService.Processor<TestThriftService.Iface>(
					new TestThriftServiceImpl());

			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(
					serverTransport);
			ttpsArgs.processor(tprocessor);
			ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());

			// 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
			TServer server = new TThreadPoolServer(ttpsArgs);
			server.serve();
		}
		catch (Exception e)
		{
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 非阻塞式IO模型
	 */
	public static void nonblockIOModel()
	{
		try
		{
			System.out.println("HelloWorld TThreadPoolServer start ....");

			TProcessor tprocessor = new TestThriftService.Processor<TestThriftService.Iface>(
					new TestThriftServiceImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(
					tnbSocketTransport);
			tnbArgs.processor(tprocessor);
			tnbArgs.transportFactory(new TFramedTransport.Factory());
			tnbArgs.protocolFactory(new TBinaryProtocol.Factory());
 
			// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
			TServer server = new TNonblockingServer(tnbArgs);
			server.serve();
		}
		catch (Exception e)
		{
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 半同步半异步模型
	 */
	public static void halfAsyncAndSyncModel()
	{
		try
		{
			System.out.println("HelloWorld TThreadPoolServer start ....");

			TProcessor tprocessor = new TestThriftService.Processor<TestThriftService.Iface>(
					new TestThriftServiceImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
			thhsArgs.processor(tprocessor);
			thhsArgs.transportFactory(new TFramedTransport.Factory());
			thhsArgs.protocolFactory(new TBinaryProtocol.Factory());
 
			//半同步半异步的服务模型
			TServer server = new THsHaServer(thhsArgs);
			server.serve();
		}
		catch (Exception e)
		{
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 异步模型
	 */
	public static void asyncModel()
	{
		try
		{
			System.out.println("HelloWorld TThreadPoolServer start ....");

			TProcessor tprocessor = new TestThriftService.Processor<TestThriftService.Iface>(
					new TestThriftServiceImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(
					tnbSocketTransport);
			tnbArgs.processor(tprocessor);
			tnbArgs.transportFactory(new TFramedTransport.Factory());
			tnbArgs.protocolFactory(new TBinaryProtocol.Factory());
 
			// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
			TServer server = new TNonblockingServer(tnbArgs);
			server.serve();
		}
		catch (Exception e)
		{
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}
}
