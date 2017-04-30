package com.keepthinker.example.general.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RejectedPolicyMain {

	public static void main(String[] args) {
		System.out.println("caller run policy");
		ExecutorService ex = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MINUTES
				, new LinkedBlockingQueue<Runnable>(3), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.CallerRunsPolicy());
		for(int i = 0; i < 10; i++){
			ex.execute(new MyWorker());
		}
		ex.shutdown();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("abort policy");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//the following can only support up to 5 tasks in the pool, two running and three waiting
		ex = new ThreadPoolExecutor(2, 3, 0, TimeUnit.MINUTES
				, new LinkedBlockingQueue<Runnable>(2), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
		try{
		for(int i = 0; i < 15; i++){
			ex.execute(new MyWorker());
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		ex.shutdown();
		System.out.println("end");
	}

	public static class MyWorker implements Runnable{
		private static final AtomicInteger counter = new AtomicInteger(0);
		private int id;
		
		public MyWorker(){
			id = counter.incrementAndGet();
		}

		@Override
		public void run() {
			System.out.println("Thread " + Thread.currentThread().getId() + " counter = " + id);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
