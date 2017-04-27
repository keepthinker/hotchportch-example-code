package com.keepthinker.example.spring.ioc.AtBean;

import com.keepthinker.example.spring.ioc.model.Tiger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanInsideComponent {
	
	@Bean
	public Tiger compTiger(@Value("Kaser") String name){
		Tiger tiger = new Tiger();
		tiger.setName(name);
		return tiger;
	}
	
}
