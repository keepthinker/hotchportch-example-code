package com.keepthinker.spring.general.validator;

public class Customer extends Person{
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
