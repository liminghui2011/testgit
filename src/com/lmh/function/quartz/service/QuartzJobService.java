package com.lmh.function.quartz.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lmh.function.quartz.entity.ScheduleJob;

@Transactional
@Repository
@Service("quartzJobService")
public class QuartzJobService
{
    /**
     * 获取所有的定时任务记录信息
     * @return
     */
	@Transactional(propagation = Propagation.SUPPORTS)
    public List<ScheduleJob> getAllJobs(){
		return null;
    }
    
    /**
     * 根据id获取任务记录
     * @param id
     * @return
     */
	@Transactional(propagation = Propagation.SUPPORTS)
    public ScheduleJob getScheduleJobById(int id){
		return new ScheduleJob();
    }
    
    /**
     * 插入一条定时任务记录
     * @param job
     */
    public void inserJob(ScheduleJob job) {
    	
	}
    
    /**
     * 更新一条定时任务记录
     * @param job
     */
    public void updateJob(ScheduleJob job) {
    	
	}
    
    /**
     * 删除一条定时任务记录
     * @param job
     */
    public void deleteJob(int id) {
    	
	}    
}
