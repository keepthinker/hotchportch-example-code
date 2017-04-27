package com.keepthinker.example.general.sql.example;

import com.keepthinker.example.general.sql.Column;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable{
	
	private static final long serialVersionUID = -5490095776612143664L;
	private int id;
	private String name;
	private double wealth;
	@Column(name = "phone_number")
	private long phoneNumber;
	@Column(name = "is_married")
	private boolean married;
	private Date birthDay;
	
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
	public double getWealth() {
		return wealth;
	}
	public void setWealth(double wealth) {
		this.wealth = wealth;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean isMarried) {
		this.married = isMarried;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}
