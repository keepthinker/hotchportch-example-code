package com.keepthinker.example.general.xml.dynamictable;

import java.util.Date;

public class Utils {
	public Class<?> convertToClass(String type){
		if(type.equals("string")){
			return String.class;
		}else if(type.equals("int")){
			return Integer.class;
		}else if(type.equals("long")){
			return Long.class;
		}else if(type.equals("float")){
			return Float.class;
		}else if(type.equals("double")){
			return Double.class;
		}else if(type.equals("date")){
			return Date.class;
		}
		
		return null;
	}
}
