package com.lmh.function.quartz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 定时任务封装类
 * @author   xiaohe
 */
@Entity
@Table(name = "t_job")
public class ScheduleJob implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8261517550603832958L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
    /** 任务名称 */
	@Column(name="job_name")
    private String jobName;
    
    /** 任务分组 */
	@Column(name="job_group")
    private String jobGroup;
    
    /** 任务状态 0禁用 1启用 2删除*/
	@Column(name="job_status")
    private String jobStatus;
    
    /** 任务运行时间表达式 */
	@Column(name="cron_expression")
    private String cronExpression;
    
    /** 任务执行类 */
	@Column(name="bean_class")
    private String beanClass;
    
    /** 任务执行方法 */
	@Column(name="execute_method")
    private String executeMethod;
    
    /** 任务创建时间 */
	@Column(name = "create_time")
    private String createTime;
    
    /** 任务更新时间 */
	@Column(name = "update_time")
    private String updateTime;
    
    /** 任务描述 */
	@Column(name = "job_desc")
    private String jobDesc;
	
	@Column(name="job_minute")
	private Integer jobMinute;

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobGroup()
    {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus()
    {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression()
    {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }

    public String getBeanClass()
    {
        return beanClass;
    }

    public void setBeanClass(String beanClass)
    {
        this.beanClass = beanClass;
    }

    public String getExecuteMethod()
    {
        return executeMethod;
    }

    public void setExecuteMethod(String executeMethod)
    {
        this.executeMethod = executeMethod;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getJobDesc()
    {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc)
    {
        this.jobDesc = jobDesc;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getJobMinute() {
		return jobMinute;
	}

	public void setJobMinute(Integer jobMinute) {
		this.jobMinute = jobMinute;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
    
}
