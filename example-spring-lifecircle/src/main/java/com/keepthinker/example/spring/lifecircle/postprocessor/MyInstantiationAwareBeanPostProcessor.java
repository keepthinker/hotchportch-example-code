package com.keepthinker.example.spring.lifecircle.postprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    private static Logger logger = LoggerFactory.getLogger(MyInstantiationAwareBeanPostProcessor.class);

    /**
     * 执行顺序1
     * 实例化前置处理方法，也就是在Bean没有生成之前执行。（注意：这里所说的是Bean未生成指的是Bean没有走spring定义创建Bean的流程，也就是doCreateBean()方法。）
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        logger.info("postProcessBeforeInstantiation|beanClass:{}|beanName:{}", beanClass, beanName);
        return null;
    }

    /**
     * 执行顺序2
     */
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
