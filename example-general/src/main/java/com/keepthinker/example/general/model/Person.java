package com.keepthinker.example.general.model;

import java.util.Arrays;

public class Person {
	private String name;
	private String birthplace;
	private int id;
	private long wealth;
	private String[] others;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getWealth() {
		return wealth;
	}
	public void setWealth(long wealth) {
		this.wealth = wealth;
	}
	public String[] getOthers() {
		return others;
	}
	public void setOthers(String[] others) {
		this.others = others;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", birthplace=" + birthplace + ", id="
				+ id + ", wealth=" + wealth + ", others="
				+ Arrays.toString(others) + "]";
	}
	
	
}
