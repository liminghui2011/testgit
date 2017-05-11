package com.thrift.client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.thrift.datatype.ResultStr;
import com.thrift.service.TestThriftService;
import com.thrift.service.TestThriftService.AsyncClient.getStr_call;

public class ThriftClientMain
{

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 7911;
	public static final int TIMEOUT = 30000;
 
	public static void main(String[] args)
	{
		singleOrPoolClientModel();
		nonblockIOClientModel();
		new ThriftClientMain().asyncClient();
	}

	/**
	 *	单线程/线程池客户端模型
	 */
	public static void singleOrPoolClientModel() {
		TTransport transport = null;
		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			// 协议要和服务端一致,有二进制格式、压缩格式、JSON格式
			TProtocol protocol = new TBinaryProtocol(transport);
			// TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
			TestThriftService.Client client = new TestThriftService.Client(
					protocol);
			transport.open();
			ResultStr res = client.getStr("test1", "test2");
			System.out.println("Thrify client result =: " + res);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}
	
	/**
	 *	非阻塞式IO/半同步半异步客户端模型
	 */
	public static void nonblockIOClientModel() {
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT));
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			TestThriftService.Client client = new TestThriftService.Client(
					protocol);
			transport.open();
			ResultStr res = client.getStr("test1", "test2");
			System.out.println("Thrify client result =: " + res);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}
	
	/**
	 * 异步客户端
	 */
	public void asyncClient() {
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager();
			TNonblockingTransport transport = new TNonblockingSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT);

			TProtocolFactory tprotocol = new TBinaryProtocol.Factory();
			TestThriftService.AsyncClient asyncClient = new TestThriftService.AsyncClient(
					tprotocol, clientManager, transport);
			System.out.println("Client start .....");

			CountDownLatch latch = new CountDownLatch(1);
			AsynCallback callBack = new AsynCallback(latch);
			System.out.println("call method sayHello start ...");
			asyncClient.getStr("test1", "test2", callBack);
			System.out.println("call method sayHello .... end");
			boolean wait = latch.await(30, TimeUnit.SECONDS);
			System.out.println("latch.await =:" + wait);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("startClient end.");
	}

	class AsynCallback implements AsyncMethodCallback<getStr_call> {
		private CountDownLatch latch;

		public AsynCallback(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void onComplete(getStr_call response) {
			System.out.println("onComplete");
			try {
				// Thread.sleep(1000L * 1);
				System.out.println("AsynCall result =:"
						+ response.getResult().toString());
			} catch (TException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}

		@Override
		public void onError(Exception exception) {
			System.out.println("onError :" + exception.getMessage());
			latch.countDown();
		}
	}
}
