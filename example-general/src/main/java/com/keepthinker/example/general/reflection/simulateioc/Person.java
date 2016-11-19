package com.keepthinker.example.general.reflection.simulateioc;

@Component
public class Person {
	public String name;
	
	public Person(){
		name = "John";
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void say(){
		System.out.println("I am " + name);
	}
	
	public String toString(){
		return Person.class.getSimpleName();
	}
}
