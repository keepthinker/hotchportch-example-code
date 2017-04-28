package com.keepthinker.example.general.concurrency;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletionServiceMain {
	private static final AtomicInteger ATINT = new AtomicInteger();

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(20);
		final ExecutorCompletionService<String> completionService =
				new ExecutorCompletionService<>(executor);
		final int size = 10;
		for(int i = 0; i < size; i++){
			completionService.submit(generateWorker());
		}

		(new Thread(){
			public void run(){
				for(int i = 0; i < size; i++){
					try {
						System.out.println(completionService.take().get());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		executor.shutdown();
	}

	private static Callable<String> generateWorker(){
		Callable<String> worker = new Callable<String>() {

			@Override
			public String call() throws Exception {
				Random random = new Random();
				long timeLen = Math.abs(random.nextLong() % 10000);
				System.out.println("sleep " + timeLen);
				Thread.sleep(timeLen);
				System.out.println("sleep " + timeLen);
				return String.valueOf(ATINT.incrementAndGet());
			}

		};
		return worker;
	}

}
