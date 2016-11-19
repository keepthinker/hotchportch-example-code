package com.keepthinker.example.general.reflection.simulateioc;


public class Main {

	public static void main(String[] args) throws Exception {
		Container container = new Container();
		Person master = (Person)container.getObject("person");
		master.say();
		
		Calculator cal = (Calculator)container.getObject("calculator");
		System.out.println(cal.plus(1, 2));
		
		Person mastet2 = cal.getMaster();
		System.out.println("compare : " + (master == mastet2));
		 mastet2.say();
	}

}
