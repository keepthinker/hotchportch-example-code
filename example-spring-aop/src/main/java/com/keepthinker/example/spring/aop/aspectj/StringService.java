package com.keepthinker.example.spring.aop.aspectj;

public class StringService {
	private String str = "string";

	public String getStr(String suffix) {
		return str + ":" + suffix;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
}
