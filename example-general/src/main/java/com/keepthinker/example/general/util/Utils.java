package com.keepthinker.example.general.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class Utils {

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

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
