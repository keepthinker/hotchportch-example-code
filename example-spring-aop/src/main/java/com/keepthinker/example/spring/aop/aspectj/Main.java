package com.keepthinker.example.spring.aop.aspectj;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
	public static void main(String[] args){
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AspectJContext.class);
		StringService service = context.getBean(StringService.class);

		System.out.println("main string: " + service.getStr("suffix"));
		
		context.registerShutdownHook();
		context.close();
	}
}
