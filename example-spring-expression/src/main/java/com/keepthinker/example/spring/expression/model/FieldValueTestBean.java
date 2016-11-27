package com.keepthinker.example.spring.expression.model;

import org.springframework.beans.factory.annotation.Value;

public class FieldValueTestBean {
	
	@Value(value = "#{ systemProperties['user.name'] }")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
