package com.keepthinker.example.spring.aop;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.Base64Utils;

public class AopMain{
	private static Logger logger = Logger.getLogger(AopMain.class);
	private static AbstractApplicationContext context = new AnnotationConfigApplicationContext(AnnotationContext.class);
    public static void main( String[] args ){
		ManageService<String> stringService = (ManageService<String>)context.getBean("stringManageServiceProxy");
    	logger.info("stringService : " + stringService.get());

    	CountingBeforeAdvice countingBeforeAdvice = (CountingBeforeAdvice)context.getBean("countingBeforeAdvice");
    	logger.info(countingBeforeAdvice.getCount());

    	CountingAfterReturningAdvice countingAfterReturningAdvice = (CountingAfterReturningAdvice)context.getBean("countingAfterReturningAdvice");
    	logger.info(countingAfterReturningAdvice.getCount());
    	
    	logger.info("----------------------------------");
    	
    	ManageService<Object> objectService = (ManageService<Object>) context.getBean("objectManageServiceProxy");
    	logger.info("objectService : " + objectService.get());
    }
}
