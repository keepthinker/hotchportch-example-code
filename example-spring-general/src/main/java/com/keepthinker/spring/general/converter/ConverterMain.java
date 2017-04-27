package com.keepthinker.spring.general.converter;

import org.apache.log4j.Logger;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.ArrayList;
import java.util.List;

public class ConverterMain {
	private static final Logger LOGGER = Logger.getLogger(ConverterMain.class);
	
	public static void main(String[] args){

		DefaultConversionService cs = new DefaultConversionService();
		LOGGER.info(cs.convert("132", Integer.class)); 

		List<Integer> input = new ArrayList<Integer>();
		input.add(2);
		LOGGER.info(cs.convert(input,
				TypeDescriptor.forObject(input), // List<Integer> type descriptor
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(String.class))));
	}
}
