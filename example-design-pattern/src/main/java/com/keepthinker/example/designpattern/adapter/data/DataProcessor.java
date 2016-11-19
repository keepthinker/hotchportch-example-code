package com.keepthinker.example.designpattern.adapter.data;

public class DataProcessor {
	private DataGetter dataGetter;
	private String str;
	public DataProcessor(DataGetter getter) {
		this.dataGetter = getter;
	}
	
	public String getData(){
		str = dataGetter.readString();
		return str;
	}
}
