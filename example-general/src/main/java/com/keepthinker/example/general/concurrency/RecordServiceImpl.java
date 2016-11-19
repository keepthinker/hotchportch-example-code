package com.keepthinker.example.general.concurrency;

public class RecordServiceImpl implements RecordService{

	@Override
	public void persist(String record) {
		System.out.println("Save a record : " + record);
	}

}
