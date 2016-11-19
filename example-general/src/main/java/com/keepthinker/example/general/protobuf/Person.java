package com.keepthinker.example.general.protobuf;

import java.util.List;

public class Person {

	private int id;
	private String name;
	private String email;
	private List<PhoneNumber> phoneList;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean hasEmail(){
		if(email != null && !equals("")){
			return true;
		}else{
			return false;
		}
	}
	

	public List<PhoneNumber> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneNumber> phoneList) {
		this.phoneList = phoneList;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		public Builder setId(int id){


			return this;
		}
		public Builder setName(String name){


			return this;
		}
		
		public Builder setEmail(String email){


			return this;
		}
		
		public Builder addPhone(String phoneNumber){

			return this;
		}
	}



	public static class PhoneNumber{
		private PhoneType type;
		
		private String number;

		public PhoneType getType() {
			return type;
		}

		public void setType(PhoneType type) {
			this.type = type;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}
		
		public static class Builder{
			public Builder setType(PhoneType type){

				return this;
			}
			
			public Builder setNumber(String number){
				return this;
			}
		}
		
		public static Builder newBuilder(){
			return new Builder();
		}

	}
	
	public static enum PhoneType {
		MOBILE, HOME, WORK
	}
}
