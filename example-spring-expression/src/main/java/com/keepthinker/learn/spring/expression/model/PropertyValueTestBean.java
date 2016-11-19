package com.keepthinker.example.spring.expression.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyValueTestBean{
	
	private String defaultLocale;
	
	@Value("#{ systemProperties['user.country'] }")
	public void setDefaultLocale(String defaultLocale)
	{
		this.defaultLocale = defaultLocale;
	}
	public String getDefaultLocale()
	{
		return this.defaultLocale;
	}
}