package com.keepthinker.example.spring.ioc.cglib;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.keepthinker.example.spring.ioc.cglib.CglibBean;

public class CglibMain {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CglibBean.class);
		CglibBean cglibBean = context.getBean(CglibBean.class);
		cglibBean.printAnimal();
		cglibBean.printAnimal();
		cglibBean.printAnimal();

		cglibBean.printNewAnimal();
		cglibBean.printNewAnimal();
		
		context.close();
	}
}
