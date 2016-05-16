package com.lmh.function.utils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class SpringWiredBean extends SpringBeanAutowiringSupport {

    /**
     * 自动装配注解会让Spring通过类型匹配为beanFactory注入示例
     */
    @Autowired
    private BeanFactory beanFactory;

    private SpringWiredBean() {
    }

    private static SpringWiredBean instance;

    /**
     * 实例方法
     * 使用的时候先通过getInstance方法获取实例
     * 
     * @param beanId
     * @return
     */
    public Object getBeanById(String beanId) {
        return beanFactory.getBean(beanId);
    }

    public static SpringWiredBean getInstance() {
        if(null == instance)
        {
            instance = new SpringWiredBean();
        }
        return instance;
    }
}