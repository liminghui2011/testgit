
package com.lmh.function.cache;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * 操作缓存的工具类，包括获取缓存客户端实例，往缓存保存数据以及从缓存获取数据的方法
 * @author liminghui
 */
@Service("mencached")
public class MemcachedHelper
{
	private static Logger log	= LoggerFactory.getLogger(MemcachedHelper.class);
	
    @Resource(name = "memcachedClient")
    private MemcachedClient client;
    
    public MemcachedHelper() throws IOException
    {
    }

    public boolean setValue(String key, int exp, Object obj)
            throws TimeoutException, InterruptedException, MemcachedException
    {
        return client.set(key, exp, obj);
    }

    public Object getValue(String key) throws TimeoutException,
            InterruptedException, MemcachedException
    {
        return client.get(key);
    }
    
    public boolean deleteValue(String key) throws TimeoutException, 
    	InterruptedException,  	MemcachedException
    {
    	return client.delete(key);
    }
    
    /**
     * 通过传入的key获取缓存中的数据
     * @param key 传入的key值
     * @return 缓存中key对应的value
     */
    public Object getValueFromMemcache(String key)
    {
    	Object object = null;
    	try {
			object = getValue(key);
			if (object == null)
			{
				object = getValue(key);
			}
		} catch (TimeoutException e) 
		{
			log.error("get value from cache occur error TimeoutException",e);
		} 
		catch (InterruptedException e) 
		{
			log.error("get value from cache occur error InterruptedException",e);
		} 
		catch (MemcachedException e) 
		{
			log.error("get value from cache occur error MemcachedException",e);
		}
		return object;
    }
    
    /**
     * 将传入的value值保存到键为key的缓存中去
     * @param key 传入的key值
     * @param value 待保存的value值
     * @param time 需要保存的时间长度，0为永不过期；显示设置的值不能大于2529000秒(30天)
     * 				当超过这个值时，会把时间理解为unix时间戳格式也就是距离1970.01.01的秒数偏移量。
     */
    public void setValueToMemcache(String key,Integer time, Object value)
    {
    	try {
    		if (value != null) 
    		{
    			setValue(key,time,value);
			}			
		} catch (TimeoutException e) 
		{
			log.error("set value to cache occur error TimeoutException",e);
		} 
		catch (InterruptedException e) 
		{
			log.error("set value to cache occur error InterruptedException",e);
		} 
		catch (MemcachedException e) 
		{
			log.error("set value to cache occur error MemcachedException",e);
		}
    }

    /**
     * 从缓存中移除指定key值的value值
     * @param key 
     */
    public void deleteValueFromMemcache(String key)
    {
    	try {
			deleteValue(key);
		} catch (TimeoutException e) 
		{
			log.error("delete value from cache occur error TimeoutException",e);
		} 
		catch (InterruptedException e) 
		{
			log.error("delete value from cache occur error InterruptedException",e);
		} 
		catch (MemcachedException e) 
		{
			log.error("delete value from cache occur error MemcachedException",e);
		}
    }
}
