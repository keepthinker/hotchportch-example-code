package com.keepthinker.example.general.reflection.simulateaop;

public class ReadingService implements Reading{

	@Override
	public void read() {
		System.out.println("I am reading a book!");
	}

}
