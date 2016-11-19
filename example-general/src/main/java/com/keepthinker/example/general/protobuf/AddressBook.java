package com.keepthinker.example.general.protobuf;

import java.io.InputStream;
import java.util.List;

public class AddressBook {
	private List<Person> personList;

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	
	public static AddressBook parseFrom(InputStream inputStream){
		return null;
		
	}
	
	public static class Builder{
		
	}

	public static AddressBook.Builder newBuilder() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
