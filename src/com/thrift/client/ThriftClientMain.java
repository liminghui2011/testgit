package com.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.thrift.datatype.ResultStr;
import com.thrift.service.TestThriftService;

public class ThriftClientMain
{

	private static String THRIFT_HOST = "localhost";
	private static int THRIFT_PORT = 7911;

	public static void main(String[] args)
	{

		try
		{
			TTransport m_transport = new TSocket(THRIFT_HOST, THRIFT_PORT, 2000);
			// TFramedTransport tFramedTransport=new
			// TFramedTransport(m_transport);
			m_transport.open();

			TProtocol protocol = new TBinaryProtocol(m_transport);
			TestThriftService.Client testClient = new TestThriftService.Client(
					protocol);

			ResultStr res = testClient.getStr("test1", "test2");
			System.out.println("res = " + res);
			m_transport.close();
		}
		catch (TException e)
		{
			e.printStackTrace();
		}

		/*try
		{
			// 设置调用的服务器为本地，端口为7911
			TTransport transport = new TSocket("localhost", 7911);
			transport.open();
			// 设置传输协议为TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(transport);
			TestThriftService.Client client = new TestThriftService.Client(protocol);
			ResultStr res = client.getStr("test1", "test2");
			System.out.println("res = " + res);
			transport.close();
		}
		catch (TTransportException e)
		{
			e.printStackTrace();
		}
		catch (TException e)
		{
			e.printStackTrace();
		}*/
	}

}
