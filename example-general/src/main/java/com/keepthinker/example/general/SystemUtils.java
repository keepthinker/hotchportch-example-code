package com.keepthinker.example.general;

public class SystemUtils {
	public static void print(Object obj){
		System.out.println(obj);
	}

	public static void print(){
		System.out.println();
	}
	
	public static void print(Object[] objs){
		for(int i = 0, len = objs.length; i < len; i++){
			System.out.print(objs[i] + " | ");
		}
		print();
		
	}
}
