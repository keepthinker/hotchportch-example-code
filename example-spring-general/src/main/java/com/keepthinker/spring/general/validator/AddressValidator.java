package com.keepthinker.spring.general.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AddressValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Address.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "location", "location.empty");
	}

}
