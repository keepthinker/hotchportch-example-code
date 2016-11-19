package com.keepthinker.example.general.json;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public @JsonIgnoreProperties(ignoreUnknown = true)  
class Person{
	@JsonIgnore  
	private int id;
	private int salary;
	private Date birthDay; 
	@JsonProperty("Name")
	private String name;
	private long birthTime;
	private Sex sex;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
	public static enum Sex{
		MALE, FEMALE;
/*		private int sexInt;
		Sex(int sexInt){
			this.sexInt = sexInt;
		}
		
		public int getSexint(){
			return sexInt;
		}*/
		
	}

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
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", salary=" + salary + ", birthDay=" + birthDay + ", name=" + name + ", birthTime="
				+ birthTime + ", sex=" + sex + "]";
	}
	
}