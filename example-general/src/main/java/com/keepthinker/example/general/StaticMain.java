package com.keepthinker.example.general;

public class StaticMain extends ParentStatic{

	
	static {
//		System.out.println(st);
		st = "12";
		System.out.println("son");
	}
	
	static String st = null;

	public static void main(String[] args) {
		System.out.println(st);
	}

}


class ParentStatic {
	static {
		System.out.println("parent1");
	}
	
	static {
		System.out.println("parent2");
	}
}
