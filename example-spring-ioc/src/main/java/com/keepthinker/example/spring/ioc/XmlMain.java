package com.keepthinker.example.spring.ioc;

import com.keepthinker.example.spring.ioc.model.Animal;
import com.keepthinker.example.spring.ioc.model.Earth;
import com.keepthinker.example.spring.ioc.model.Person;
import com.keepthinker.example.spring.ioc.model.Tiger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class XmlMain{
	private static Logger logger = Logger.getLogger(XmlMain.class);
	@Autowired
	private PropertySourcesPlaceholderConfigurer configurer;

	private Map<String, Animal> animalMap;
	@Autowired
	public void setAnimalMap(Map<String, Animal> animalMap	) {
		this.animalMap = animalMap;
	}

	public Map<String, Animal> getAnimalMap() {
		return animalMap;
	}

	private static String XMLPath = "classpath:applicationContext.xml";
	public static void main(String[] args) {

		AbstractApplicationContext context = new ClassPathXmlApplicationContext(XMLPath);
		configurationAnnotation(context);
		nameOfClass(context);
		scopeAndAutowired(context);
		scope();
		inherit(context);
		factoryBean(context);
		mapAutowired(context);
		atBean(context);
		
		context.registerShutdownHook();
	}

	public static void configurationAnnotation(ApplicationContext context){
		Person person = context.getBean(Person.class);
		logger.info(person.getName());
		logger.info(person.getWealth());

		person =new Person();
		logger.info(person.getName());
		logger.info(person.getWealth());
		PropertySource<?> pro = context.getBean("main1", XmlMain.class).configurer.getAppliedPropertySources().iterator().next();
		logger.info(pro.getProperty("person.name"));
	}

	public static void nameOfClass(ApplicationContext context){
		logger.info(Inner.class.getName());
		logger.info(Inner.class.getCanonicalName());
		try {
			logger.info(Class.forName(Inner.class.getName()).newInstance());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		Inner inner = context.getBean(Inner.class);
		logger.info("Inner : " + inner);
	}

	public static void scopeAndAutowired(ApplicationContext context){
		Animal animal1 = context.getBean("animal", Animal.class);
		logger.info("animal : " + animal1.getId());
		Animal animal2 = context.getBean("animal", Animal.class);
		logger.info("animal : " + animal2.getId());

		Animal animalDuplicate = context.getBean("animalDuplicate", Animal.class);
		logger.info("animalDuplicate : " + animalDuplicate.getId());

		Earth earth1 = context.getBean("earth", Earth.class);
		logger.info("earth1 : " + earth1.getId());
		logger.info("earth1.animal : " + earth1.getAnimal().getName());//autowired a Animal with primary="true" set
		logger.info("earth1.animals.size : " + earth1.getAnimals().size());//autowired all Animal including Person

		Earth earth2 = context.getBean("earth", Earth.class);
		logger.info("earth2 : " + earth2.getId());
	}

	public static ThreadLocal<String> local = new ThreadLocal<String>();

	public static void scope(){
		local.set("thread local");
		logger.info("scope : " + local.get());

		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				logger.info("scopeNewThread : " + local.get());
				local.set("scopeNewThread local");

				local.remove();
			}
		});
		t.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("scope : " + local.get());

		local.remove();
		logger.info("scope : " + local.get());

	}

	public static void inherit(ApplicationContext context){
		Animal animal = context.getBean("inheritedAnimal", Animal.class);
		logger.info("inherited animal : " + animal.getId() + ":" + animal.getName());
		Animal animal2 = context.getBean("overrideAnimal", Animal.class);
		logger.info("inherited animal : " + animal2.getId() + ":" + animal2.getName());

	}

	public static void factoryBean(ApplicationContext context){
		Earth earth = (Earth)context.getBean("earthFromFactory");
		logger.info("earth from factory : " + earth.getId());
	}

	public static class Inner{
		@Override
		public String toString(){
			return "Inner class";
		}
	}
	
	public static void mapAutowired(ApplicationContext context){
		Map<String, Animal> animalMap = context.getBean("main1", XmlMain.class).getAnimalMap();
		for(String key : animalMap.keySet()){
			logger.info(key + " : " + animalMap.get(key).getId());
		}
	}
	
	public static void atBean(ApplicationContext context){
		Tiger compTiger = context.getBean("compTiger", Tiger.class);
		logger.info("Tiger " + compTiger.getName() + " compTiger's master is " + compTiger.getMaster().getName());
		Tiger compTiger2 = context.getBean("compTiger", Tiger.class);
		logger.info("Tiger " + compTiger2.getName() + " compTiger's master is " + compTiger2.getMaster().getName());
		
		Tiger confTiger = context.getBean("confTiger", Tiger.class);
		logger.info("Tiger confTiger's master is " + confTiger.getMaster().getName());
		
	}
}
