package com.keepthinker.example.spring.aop.aspectj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringService {
	private Logger logger = LoggerFactory.getLogger(StringService.class);
	private String str = "string";

	public String getStr(String suffix) {
		logger.info("execute getStr method with arg:{}", suffix);
		return str + ":" + suffix;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
}
