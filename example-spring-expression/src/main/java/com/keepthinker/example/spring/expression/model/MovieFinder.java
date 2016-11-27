package com.keepthinker.example.spring.expression.model;

public class MovieFinder {
	
	private int id;
	private String name;
	
	public MovieFinder() {
	}
	public MovieFinder(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
