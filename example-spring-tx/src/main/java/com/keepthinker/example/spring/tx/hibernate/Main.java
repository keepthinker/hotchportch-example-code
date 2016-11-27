package com.keepthinker.example.spring.tx.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.keepthinker.example.model.Actor;

public class Main {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	
    public static void main( String[] args ) {
    	HActorService actorService = context.getBean(HActorService.class);
    	Actor actor = new Actor();
    	actor.setId(5);
    	actor.setFirstName("Sherry");
    	actor.setLastName("Ke");
    	actorService.save(actor);
        System.out.println(actorService.get(5));
        System.out.println(actorService.getCount());
        actor.setId(0);
        actor.setFirstName("Ok");
        actorService.saveAndPrint(actor);
        System.out.println(actorService.get(0));
    }
}