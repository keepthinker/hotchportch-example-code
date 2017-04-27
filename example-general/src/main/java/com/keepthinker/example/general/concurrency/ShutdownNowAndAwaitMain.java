package com.keepthinker.example.general.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutdownNowAndAwaitMain {
	public static void main(String[] args) throws InterruptedException{
		ExecutorService exc = Executors.newCachedThreadPool();
		exc.execute(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("sleep");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("dafjosd");
			}
		});
		Thread.sleep(100);
		System.out.println(exc.shutdownNow().size());
		System.out.println(exc.awaitTermination(1000, TimeUnit.MILLISECONDS));
		System.out.println("yeah");

	}
}
