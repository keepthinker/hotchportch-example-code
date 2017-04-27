package com.keepthinker.example.spring.tx.springjdbc;

import com.keepthinker.example.model.Actor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	
    public static void main( String[] args ) {
    	ActorService actorService = context.getBean(ActorService.class);
    	Actor actor = new Actor();
    	actor.setId(5);
    	actor.setFirstName("Sherry");
    	actor.setLastName("Ke");
    	actorService.save(actor);
        System.out.println(actorService.find(5));
    }
}
