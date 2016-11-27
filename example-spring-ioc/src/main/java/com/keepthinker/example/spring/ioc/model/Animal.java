package com.keepthinker.example.spring.ioc.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;

public class Animal{
	private static Logger logger = Logger.getLogger(Animal.class);
	private static int counter = 0;
	private int id;
	protected String name;
	
	public void init(){
		logger.info("animal : " + this.id + " init");
	}
	
	public Animal(){
		counter++;
		id = counter;
		logger.info("new : " + id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	@PostConstruct
	public void postConstruct(){
		logger.info("animal : " + this.id + " post construct");
	}
	
	@PreDestroy
	public void preDestroy(){
		logger.info("animal : " + this.id + " pre destroy");
	}
}
