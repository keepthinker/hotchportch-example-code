package com.keepthinker.example.spring.ioc.postprocessor;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    private static Logger logger = Logger.getLogger(MyInstantiationAwareBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInstantiation(Class<?> aClass, String s) throws BeansException {
        logger.info("postProcessBeforeInstantiation");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object o, String s) throws BeansException {
        logger.info("postProcessAfterInstantiation");
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, PropertyDescriptor[] propertyDescriptors, Object o, String s) throws BeansException {
        logger.info("postProcessPropertyValues");
        return propertyValues;
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        logger.info("postProcessBeforeInitialization");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        logger.info("postProcessPropertyValues");
        return o;
    }
}
