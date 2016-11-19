package com.keepthinker.example.general.reflection.simulateioc;

@Component
public class Calculator {
	@Autowired
	private Person master;
	
	public Person getMaster(){
		return master;
	}
	
	public int plus(int a, int b){
		return a + b;
	}
}
