package com.keepthinker.example.spring.ioc.postprocessor;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;

public class AnimalNamePrefixAddBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static Logger logger = Logger.getLogger(AnimalNamePrefixAddBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("animal");
        MutablePropertyValues values = beanDefinition.getPropertyValues();
        Object val = values.get("name");
        if (val != null) {
            if (val instanceof TypedStringValue){
                values.add("name", "animal:" + ((TypedStringValue)val).getValue());
            }
        }
        logger.info("postProcessBeanFactory");
    }
}
