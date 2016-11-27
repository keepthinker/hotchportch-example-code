package com.keepthinker.example.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.keepthinker.example.spring.ioc.model.Panda;

@Configuration
public class AnnotationConfig {
	
	@Bean
	public Panda panda(){
		Panda panda = new Panda();
		panda.setName("panda");
		return panda;
	}
}
