package com.keepthinker.example.general.runtime.compile;

public class Main {
	public static void main(String[] args){
		DynamicClassManager dynamicClass = new DynamicClassManager();
		dynamicClass.compile("javafile/Person.java");
		System.out.println(dynamicClass.newInstance("com.keepthinker.example.general.runtime.model.Person"));
	}
}
