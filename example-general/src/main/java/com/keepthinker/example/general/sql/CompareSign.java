package com.keepthinker.example.general.sql;

public enum CompareSign {
	LESS_THAN(" < "),
	LESS_THAN_OR_EQUAL_TO(" <= "),
	GREATER_THAN(" > "),
	GREATER_THAN_OR_EQUAL_TO(" >= ");
	
	private final String sign;
	
	private CompareSign(String sign){
		this.sign = sign;
	}
	
	@Override
	public String toString(){
		return sign;
	}
	
}
