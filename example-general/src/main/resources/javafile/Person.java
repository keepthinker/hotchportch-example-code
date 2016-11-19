package com.keepthinker.example.general.runtime.model;
import java.util.Date;

public class Person{
	private int id;
	private int salary;
	private Date birthDay; 
	private String name;
	private long birthTime;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBirthTime(	) {
		return birthTime;
	}
	public void setBirthTime(long birthTime) {
		this.birthTime = birthTime;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", salary=" + salary + ", birthDay="
				+ birthDay + ", name=" + name + ", birthTime=" + birthTime
				+ "]";
	}
}