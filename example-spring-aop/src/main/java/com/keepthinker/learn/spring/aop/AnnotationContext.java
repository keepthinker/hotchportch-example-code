package com.keepthinker.example.spring.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationContext {
	@Bean
	public RegexpMethodPointcutAdvisor settersAndAbsquatulateAdvisor(){
		RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
		advisor.setAdvice(debugInterceptor());
		advisor.setPattern(".*.*");
		return advisor;
	}
	
	@Bean
	public ManageService<Object> objectManageService(){
		return new ObjectManageServiceImpl();
	}
	
	@SuppressWarnings("unchecked")
	@Bean
	public ManageService<Object> objectManageServiceProxy(){
		ProxyFactory factory = new ProxyFactory(objectManageService());
		factory.addAdvisor(settersAndAbsquatulateAdvisor());
		return (ManageService<Object>) factory.getProxy();
	}
	
	@Bean
	public Advice debugInterceptor(){
		return new DebugInterceptor();
	}
	
	@Bean
	public Advice countingAfterReturningAdvice(){
		return new CountingAfterReturningAdvice();
	}
	
	@Bean
	public Advice countingBeforeAdvice(){
		return new CountingBeforeAdvice();
	}
	
	@Bean
	public ManageService<String> stringManageService(){
		return new StringManageService();
	}
	
	@Bean
	public ProxyFactoryBean stringManageServiceProxy(){
		ProxyFactoryBean factoryBean = new ProxyFactoryBean();
		factoryBean.setTarget(stringManageService());
		factoryBean.setInterceptorNames("debugInterceptor", "countingBeforeAdvice", "countingAfterReturningAdvice");
		return factoryBean;
	}
	
}
