package com.keepthinker.example.general.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	private static ObjectMapper mapper = new ObjectMapper();

	public static ClassLoader getContextClassloader(){
		return Thread.currentThread().getContextClassLoader();
	}
	private static String path  = getContextClassloader().getResource("").getPath();
	public static String getContextClasspath(){
		return path;
	}

	public static String toJsonString(Object obj){
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			return "";
		}
	}
}
