package com.keepthinker.example.spring.ioc.model;

import javax.annotation.Resource;

public class Tiger extends Animal{
	private static int count = 0;
	private int id; 
	
	private String name;
	
	private Person master;
	
	public Tiger(){
		super();
		id = count++;
	}
	
	public Tiger(String name){
		this.name = name;
	}
	
	public String getName() {
		return name + "-" + id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Person getMaster() {
		return master;
	}
	
	@Resource(name = "person")
	public void setMaster(Person master) {
		this.master = master;
	}
	
	
}
