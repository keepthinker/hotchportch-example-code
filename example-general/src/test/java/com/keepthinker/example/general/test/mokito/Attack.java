package com.keepthinker.example.general.test.mokito;

public interface Attack {
	public boolean ready();
	
	public Result shot();
	
	public Result shot(int times);
}
