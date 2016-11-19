package com.keepthinker.example.general.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
	private static  RecordService rs = new RecordServiceImpl();
	public static void main(String[] args) throws InterruptedException {
		ExecutorService	es = Executors.newFixedThreadPool(5);
		
		es.execute(new Task(rs, "imei1"));
		es.execute(new Task(rs, "imei2"));
		es.execute(new Task(rs, "imei3"));
		es.execute(new Task(rs, "imei4"));
		es.execute(new Task(rs, "imei5"));
		es.execute(new Task(rs, "imei6"));
		es.execute(new Task(rs, "imei7"));
		
		es.shutdown();
		
		System.out.println("all tasks are scheduled");
	}
}
