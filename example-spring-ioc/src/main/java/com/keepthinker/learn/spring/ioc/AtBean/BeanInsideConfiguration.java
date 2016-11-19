package com.keepthinker.example.spring.ioc.AtBean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.keepthinker.example.spring.ioc.model.Tiger;

@Configuration
public class BeanInsideConfiguration {
	
	@Bean//(name = "confTiger") has the same effect as following @Qualifier
	@Qualifier("confTiger")
	public Tiger confTiger(){
		return new Tiger();
	}
}
