package com.keepthinker.example.spring.ioc;

import com.keepthinker.example.spring.ioc.model.Panda;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationMain {
	private static Logger logger = Logger.getLogger(AnnotationMain.class);
	public static void main(String[] args){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
//		context.scan("com.keepthinker.spring.ioc");
//		context.refresh();
		Panda panda = context.getBean(Panda.class);
		logger.info(panda.getName());
		context.registerShutdownHook();
		context.close();
	}
}
