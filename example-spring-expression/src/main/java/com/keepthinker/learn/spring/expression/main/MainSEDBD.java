package com.keepthinker.example.spring.expression.main;

import java.util.Properties;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.keepthinker.example.spring.expression.model.FieldValueTestBean;
import com.keepthinker.example.spring.expression.model.Inventor;
import com.keepthinker.example.spring.expression.model.MovieFinder;
import com.keepthinker.example.spring.expression.model.NumberGuess;
import com.keepthinker.example.spring.expression.model.PropertyValueTestBean;
import com.keepthinker.example.spring.expression.model.ShapeGuess;
import com.keepthinker.example.spring.expression.model.SimpleMovieLister;
import com.keepthinker.example.spring.expression.model.TaxCalculator;

/**
 * Expression support for defining bean definitions
 * 
 * Bean definitions are in xml and AnnotatedContext.java
 * 
 * @author keshengkai
 */
public class MainSEDBD {
	private static final String XMLPath = "applicationContext.xml";
	
	public static void main(String[] args){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(XMLPath);
//		ApplicationContext context = new FileSystemXmlApplicationContext("//home/keshengkai/workspace/example-spring-expression/src/main/resources/applicationContext.xml");
		NumberGuess numberGuess = context.getBean(NumberGuess.class);
		System.out.println(numberGuess.getRandomNumber());
		TaxCalculator taxCalculator = context.getBean(TaxCalculator.class);
		System.out.println(taxCalculator.getDefaultLocale());

		Properties properties = (Properties) context.getBean("systemProperties");
		for(Object obj : properties.keySet()){
			System.out.printf("%30s : %-30s\n", obj, properties.getProperty((String) obj));
		}
		System.out.println(properties.getProperty("user.name"));
		
		ShapeGuess shapeGuess = (ShapeGuess)context.getBean("shapeGuess");
		System.out.println(shapeGuess.getInitialShapeSeed());
		
		FieldValueTestBean fieldValueTestBean = context.getBean(FieldValueTestBean.class);
		System.out.println(fieldValueTestBean.getUserName());
		

		PropertyValueTestBean propertyValueTestBean = context.getBean(PropertyValueTestBean.class);
		System.out.println(propertyValueTestBean.getDefaultLocale());
		
		SimpleMovieLister simpleMovieLister =  context.getBean("simpleMovieLister", SimpleMovieLister.class);
		MovieFinder movieFinder = simpleMovieLister.getMovieFinder();
		System.out.println(movieFinder.getId() + ":" + movieFinder.getName() + " is living in " 
		+ simpleMovieLister.getDefaultLocale());
		
		Inventor inventor = context.getBean(Inventor.class);
		System.out.println(inventor.getPlaceOfBirth().getCity());
		
		context.registerShutdownHook();
		context.close();
	}

}
