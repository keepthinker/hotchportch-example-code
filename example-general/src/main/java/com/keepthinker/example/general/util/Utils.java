package com.keepthinker.example.general.util;

public class Utils {
	public static ClassLoader getContextClassloader(){
		return Thread.currentThread().getContextClassLoader();
	}
	private static String path  = getContextClassloader().getResource("").getPath();
	public static String getContextClasspath(){
		return path;
	}
}
