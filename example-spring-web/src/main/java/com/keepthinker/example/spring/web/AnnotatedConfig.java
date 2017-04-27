package com.keepthinker.example.spring.web;

import com.keepthinker.example.spring.web.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AnnotatedConfig {
	@Bean
	@Scope("request")
	public Person personRequest(){
		return new Person();
	}
	
	@Bean
	@Scope("session")
	public Person personSession(){
		return new Person();
	}
}
