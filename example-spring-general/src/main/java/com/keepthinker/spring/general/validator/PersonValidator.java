package com.keepthinker.spring.general.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors e) {
//		ValidationUtils.rejectIfEmpty(e, "firstName", "name.empty");
//		ValidationUtils.rejectIfEmpty(e, "surname", "name.empty");
//		ValidationUtils.rejectIfEmpty(e, "gender", "gender.empty");
		Person p = (Person) obj;
		if (p.getAge() < 0) {
			e.rejectValue("age", "negativevalue");
		} else if (p.getAge() > 110) {
			e.rejectValue("age", "too.darn.old");
		}
	}

}
