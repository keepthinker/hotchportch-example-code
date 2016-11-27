package com.keepthinker.example.spring.aop;

public class StringManageService implements ManageService<String> {

	@Override
	public String get() {
		return "String";
	}

	@Override
	public void put() {
		// TODO Auto-generated method stub
		
	}

}
