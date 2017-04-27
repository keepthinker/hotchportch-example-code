package com.keepthinker.example.general.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AwaitThreadPollMain {
	public static void main(String[] args) throws InterruptedException{
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new Worker(1));
		es.execute(new Worker(2));
		es.execute(new Worker(3));
		es.shutdown();
		while(es.awaitTermination(1, TimeUnit.SECONDS) == false){
			System.out.println("not finished, wait again");
		};
		System.out.println("completed");
	}
	
	private static class Worker implements Runnable{

		private int id;
		public Worker(int id){
			this.id = id;
		}

		@Override
		public void run()  {

			System.out.println("worker " + id + " to run");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
