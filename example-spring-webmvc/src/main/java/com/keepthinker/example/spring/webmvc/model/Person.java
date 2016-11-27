package com.keepthinker.example.spring.webmvc.model;

public class Person {
	private String name;
	private String birthplace;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public Person(String name, String birthplace) {
		super();
		this.name = name;
		this.birthplace = birthplace;
	}
	
}
