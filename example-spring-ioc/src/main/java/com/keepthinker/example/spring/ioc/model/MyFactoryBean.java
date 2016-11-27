package com.keepthinker.example.spring.ioc.model;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Earth>{
	private Earth earth = new Earth();

	@Override
	public Earth getObject() throws Exception {
		return earth;
	}

	@Override
	public Class<Earth> getObjectType() {
		return Earth.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
