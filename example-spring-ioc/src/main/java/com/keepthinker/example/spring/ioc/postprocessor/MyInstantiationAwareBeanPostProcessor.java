package com.keepthinker.example.spring.ioc.postprocessor;

import com.keepthinker.example.spring.ioc.model.Animal;
import com.keepthinker.example.spring.ioc.model.Earth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    private static Logger logger = LoggerFactory.getLogger(MyInstantiationAwareBeanPostProcessor.class);


    /**
     * 实例化前置处理方法，也就是在Bean没有生成之前执行。（注意：这里所说的是Bean未生成指的是Bean没有走spring定义创建Bean的流程，也就是doCreateBean()方法。）
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        logger.info("postProcessBeforeInstantiation|beanClass:{}|beanName:{}", beanClass, beanName);
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        logger.info("postProcessAfterInstantiation|bean:{}|beanName:{}", bean, beanName);
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        logger.info("postProcessPropertyValues|PropertyValues:{}|PropertyDescriptor:{}|bean:{}|beanName:{}",
                pvs, pds, bean, beanName);
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessBeforeInitialization|bean:{}|beanName:{}", bean, beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessAfterInitialization|bean:{}|beanName:{}", bean, beanName);
        return bean;
    }
}
