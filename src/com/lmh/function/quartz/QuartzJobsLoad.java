package com.lmh.function.quartz;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lmh.function.quartz.manage.JobMethod;

public class QuartzJobsLoad implements ServletContextListener
{
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        ServletContext servletContext = servletContextEvent.getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        JobMethod jobMethod = (JobMethod) applicationContext.getBean("JobMethod");
        jobMethod.init();
    }
}
