package com.keepthinker.example.spring.aop.aspectj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectJContext {

	@Bean
	LoggingHandler logginHandler(){
		return new LoggingHandler();
	}
	
	@Bean
	StringService stringService(){
		return new StringService();
	}
}
