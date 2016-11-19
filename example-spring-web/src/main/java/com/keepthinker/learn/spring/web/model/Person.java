package com.keepthinker.example.spring.web.model;

public class Person {
	private static int counter = 0;
	private int id;
	
	public Person(){
		counter++;
		id = counter;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
