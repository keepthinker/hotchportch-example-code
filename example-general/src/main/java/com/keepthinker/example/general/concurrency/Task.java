package com.keepthinker.example.general.concurrency;

public class Task implements Runnable {
	private RecordService rs;
	private String record;
	public Task(RecordService rs, String record) {
		this.rs = rs;
		this.record = record;
	}

	@Override
	public void run() {
		rs.persist(record);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
