package com.lmh.function;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.lutongnet.cps.base.model.entity.system.UserValue;
import com.lutongnet.cps.base.util.SpringUtils;
import com.lutongnet.cps.base.util.SpringWiredBean;
import com.lutongnet.cps.system.service.OperService;

import net.rubyeye.xmemcached.MemcachedClient;

//告诉framework怎么运行这个类
@RunWith(SpringJUnit4ClassRunner.class)
// bean的配置文件路径，这个是Test类的classpath路径，如果是Spring推荐的目录结构，应该在：项目目录/src/test/resources/里
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
        "classpath:root-context.xml" })
@TransactionConfiguration(defaultRollback=false)
public class JunitCaseTest extends AbstractTransactionalJUnit4SpringContextTests
{
    private MockHttpServletRequest request;
    
    @Autowired
    private OperService operService;
    
    @Resource(name = "memcachedClient")
    private MemcachedClient memcachedClient;
    
    @Before
    public void setUp() throws Exception
    {
        request = new MockHttpServletRequest();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    /**
     * 测试方法
     * @throws Exception 
     */
    @Test
    public void testMonthTable() throws Exception
    {
    	System.err.println(request);
    }
    
    @Test
    public void testCategory() throws IOException{
    	System.err.println(operService.getAllSystemUsers().size());
    	UserValue userValue = operService.getUserById(55);
    	System.err.println("1=="+operService.getAllSystemUsers().size());
    	System.err.println("1.5=="+userValue.toString());
    	userValue = operService.getUserById(55);
    	System.err.println("2=="+userValue.toString());
    	userValue = operService.getUserById(56);
    	System.err.println("3=="+userValue.toString());
    }
    
	@Test
    public void testMemcached() throws Exception{
    	memcachedClient.set("user0", 10*60, "user0");
    	memcachedClient.set("user1", 10*60, "user1");
    	memcachedClient.set("user2", 10*60, "user2");
    	memcachedClient.set("user3", 10*60, "user3");
    	memcachedClient.set("user4", 10*60, "user4");
    	Collection<InetSocketAddress> collection = memcachedClient.getAvailableServers();
    	for (InetSocketAddress inetSocketAddress : collection) {
			System.err.println("getAddress="+inetSocketAddress.getAddress());
			System.err.println("getHostName="+inetSocketAddress.getHostName());
			System.err.println("getHostString="+inetSocketAddress.getHostString());
		}
//    	KeyIterator iterator = memcachedClient.getKeyIterator(new InetSocketAddress("172.16.1.114", 11211));
//    	while(iterator.hasNext())
//    	{
//    	   String key = iterator.next();
//    	   Object obj = memcachedClient.get(key);
//    	   System.err.println("obj========="+obj);
//    	}
    }
	
	@Test
    public void testSpringInit() throws Exception{
		SpringWiredBean springWiredBean = (SpringWiredBean)SpringUtils.getApplicationContext().getBean("springWiredBean");
		System.err.println(SpringWiredBean.getInstance().showFactoryBean());
		System.err.println(SpringWiredBean.getInstance().getBeanById("aaaaa"));
	}
}
