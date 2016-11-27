package com.keepthinker.example.spring.ioc.cglib;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.keepthinker.example.spring.ioc.model.Animal;
import com.keepthinker.example.spring.ioc.model.Person;
import com.keepthinker.example.spring.ioc.model.Tiger;

@Configuration
public class CglibBean {
	@Bean
	public Person person(){
		return new Person();
	}
	
	@Bean
	public Animal myTiger(){
		return tiger();
	}

	@Bean
	public Tiger tiger(){
		Tiger tiger = new Tiger();
		tiger.setName("china's tiger");
		return tiger;
	}
	
	public void printAnimal(){
		System.out.println(myTiger().getName());
	}
	
	public void printNewAnimal(){
		System.out.println(new Tiger().getName());
	}
}
