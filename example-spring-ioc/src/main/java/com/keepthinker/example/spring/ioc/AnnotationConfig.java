package com.keepthinker.example.spring.ioc;

import com.keepthinker.example.spring.ioc.model.Panda;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {
	
	@Bean
	public Panda panda(){
		Panda panda = new Panda();
		panda.setName("panda");
		return panda;
	}
}
