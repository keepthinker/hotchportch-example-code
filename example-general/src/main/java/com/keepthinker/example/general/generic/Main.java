package com.keepthinker.example.general.generic;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args){
		print(new B());
		
		List<? extends A> ae = new ArrayList<A>();
		
		List<? super B> as = new ArrayList<A>();
		as.add(new B());
	}
	
	 public static <T> void print(List<T> list, T a){
		System.out.println(list.contains(a));
	}
	 
	public static <T extends A> void print(T a){
		System.out.println(a);
	}
	
}


class A{
	public String toString(){
		return "A";
	}
}

class B extends A{
	public String toString(){
		return "B";
	}
}