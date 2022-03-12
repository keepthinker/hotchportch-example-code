package com.keepthinker.example.spring.lifecircle.zoo;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Tiger {
	private static AtomicInteger counter = new AtomicInteger();
	private int id;
	private String name;

	public Tiger(){
		id = counter.incrementAndGet();
	}
	
	public Tiger(String name){
		this.name = name;
	}
	
	public String getName() {
		return name + "-" + id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tiger{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
