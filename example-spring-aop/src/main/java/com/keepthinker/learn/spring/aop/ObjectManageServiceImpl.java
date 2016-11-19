package com.keepthinker.example.spring.aop;

public class ObjectManageServiceImpl implements ManageService<Object>{

	@Override
	public Object get() {
		return 1000;
	}

	@Override
	public void put() {
		
	}

}
