package com.lmh.function.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.lmh.function.quartz.entity.ScheduleJob;
import com.lmh.function.quartz.executor.JobExecutor;

public class QuartzJobFactory implements Job {
    
        @Override
        public void execute(JobExecutionContext context)
                throws JobExecutionException
        {
        	System.err.println("QuartzJobFactory execute");
            ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
            
            //反射执行任务
            try
            {
                JobExecutor.invokMethod(scheduleJob);
                System.err.println("任务执行成功！");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

