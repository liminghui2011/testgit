package com.lmh.function.quartz.executor;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lmh.function.quartz.entity.ScheduleJob;

public class JobExecutor
{
    private static Log log = LogFactory.getLog(JobExecutor.class);
    
    public static void invokMethod(ScheduleJob job)
    {
        if(null == job){
            return;
        }
        String beanClass = job.getBeanClass();
        String methodName = job.getExecuteMethod();
        
        if(null == beanClass||"".equals(beanClass)||null == methodName||"".equals(methodName)){
            return;
        }
        
        Class<?> clazz;
        try
        {
        	System.err.println(beanClass+"--------"+methodName);
            clazz = Class.forName(beanClass);
            Method method = clazz.getDeclaredMethod(methodName);
            method.invoke(clazz.newInstance());
        }
        catch (Exception e)
        {
           log.error("Task "+job.getJobName()+" failed."+e.toString());
           e.printStackTrace();
        }
    }
}
