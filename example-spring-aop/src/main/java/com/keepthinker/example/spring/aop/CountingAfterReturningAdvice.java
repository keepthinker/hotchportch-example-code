package com.keepthinker.example.spring.aop;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class CountingAfterReturningAdvice implements AfterReturningAdvice {
	private static Logger logger = Logger.getLogger(CountingAfterReturningAdvice.class);
	private int count;
	public void afterReturning(Object returnValue, Method m, Object[] args, Object target)
			throws Throwable {
		logger.info("afterReturing");
		++count;
	}
	public int getCount() {
		return count;
	}
}
