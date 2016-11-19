package com.keepthinker.spring.general.oxm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OxmMain {
	public static void main(String[] args) throws Exception {
		ApplicationContext appContext =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		Application application = (Application) appContext.getBean("application");
		application.saveSettings();
		application.loadSettings();
	}
}
