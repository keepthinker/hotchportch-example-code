package com.keepthinker.example.spring.aop;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class CountingBeforeAdvice implements MethodBeforeAdvice {
	private static Logger logger = Logger.getLogger(CountingBeforeAdvice.class);
	private int count;
	public void before(Method m, Object[] args, Object target) throws Throwable {
		logger.info("before");
		++count;
	}
	public int getCount() {
		return count;
	}
}