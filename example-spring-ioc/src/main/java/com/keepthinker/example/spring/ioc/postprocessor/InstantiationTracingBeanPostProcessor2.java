package com.keepthinker.example.spring.ioc.postprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class InstantiationTracingBeanPostProcessor2  implements BeanPostProcessor, Ordered{
	private static Logger logger = LoggerFactory.getLogger(InstantiationTracingBeanPostProcessor2.class);
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info("postProcessBeforeInitialization before init1|bean:{}|beanName:{}", bean, beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info("postProcessAfterInitialization after init1|bean:{}|beanName:{}", bean, beanName);
		return bean;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}