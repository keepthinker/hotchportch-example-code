package com.keepthinker.example.spring.ioc;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class InstantiationTracingBeanPostProcessor2  implements BeanPostProcessor, Ordered{
	private static Logger logger = Logger.getLogger(InstantiationTracingBeanPostProcessor.class);
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info("before init1 : " + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info("after init1 : " + beanName);
		return bean;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}