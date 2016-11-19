package com.keepthinker.spring.general.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

public class ValidatorMain {
	public static void main(String[] args){
	}
	
	public static void person(){
		Person person = new Person();
		PersonValidator personValidator = new PersonValidator();
		Errors errors = new DirectFieldBindingResult(Person.class, "person");
		
		person.setAge((short) 150);
		personValidator.validate(person, errors);
		System.out.println(errors.toString());
	}
	
	public static void customer(){
		Customer custormer = new Customer();
		custormer.setFirstName("");
		CustomerValidator validator = new CustomerValidator(new AddressValidator());
		Errors errors = new BeanPropertyBindingResult(CustomerValidator.class, "customer");
		validator.validate(custormer, errors);
		System.out.println(errors.toString());
	}
}
