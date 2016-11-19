package com.keepthinker.example.cache.redis;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRedisMain {
	private static AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
	public static void main(String[] args){
		RetrieveService retrieveService = context.getBean(RetrieveService.class);
		System.out.println(retrieveService.retrieve("a"));
		sleep(2);
		System.out.println(retrieveService.retrieve("a"));
		sleep(2);
		System.out.println(retrieveService.retrieve("a"));
		sleep(2);
		System.out.println(retrieveService.retrieve("a"));
		sleep(2);
		System.out.println(retrieveService.retrieve("a"));
	}
	
	private static void sleep(int sec){
		try {
			Thread.sleep(1000 * sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
