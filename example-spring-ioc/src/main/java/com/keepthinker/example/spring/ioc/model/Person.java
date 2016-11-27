package com.keepthinker.example.spring.ioc.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
@PropertySources(@PropertySource(value = "classpath:config.properties"))
public class Person extends Animal{
	
	@Value("#{1e9}")  //you could just use 1e9
	private double wealth;

	public String getName() {
		return name;
	}

	@Value("${person.name}")
	public void setName(String name) {
		this.name = name;
	}
	
	public double getWealth() {
		return wealth;
	}

	public void setWealth(double wealth) {
		this.wealth = wealth;
	}
	
	//you could leave out bellowing method when you define <context:property-holder ...> in XML
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
}
