package com.keepthinker.example.spring.aop.aspectj;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
	public static void main(String[] args){
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AspectJContext.class);
		StringService service = context.getBean(StringService.class);
	
		println(service.getStr());
		
		context.registerShutdownHook();
		context.close();
	}
	
	public static void println(String str){
		System.out.println(str);
	}
}
