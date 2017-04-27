package com.keepthinker.example.general.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class AtomCasMain {
	public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(50);

	private final AtomicReference<Integer> counter = new AtomicReference<Integer>(0);

	public int getCount() {
		return counter.get();
	}

	public void add(int num) {
		while(true){
			// you must use Integer to declare oldVal because the counter use class reference to compare(verified in JVM 1.8.0_101)
			Integer oldVal = this.counter.get(); 
			Integer newVal = oldVal + num;
			if(counter.compareAndSet(oldVal, newVal)){
				return;
			};
		}
	}

	public static void main(String[] args) throws InterruptedException{
		
		AtomCasMain atomCasMain = new AtomCasMain();

		for(int i = 0; i < 100; i++){
			EXECUTOR_SERVICE.execute(new Worker(atomCasMain));
		}
		EXECUTOR_SERVICE.shutdown();
		EXECUTOR_SERVICE.awaitTermination(100, TimeUnit.SECONDS);
		System.out.println("result " + atomCasMain.getCount());

	}

	public static class Worker extends Thread {

		public Worker(AtomCasMain volatileMain){
			this.volatileMain = volatileMain;
		}

		private AtomCasMain volatileMain;
		@Override
		public void run(){
			for(int i = 0; i < 10000; i++){
				volatileMain.add(1);
			}
		}
	}

}


