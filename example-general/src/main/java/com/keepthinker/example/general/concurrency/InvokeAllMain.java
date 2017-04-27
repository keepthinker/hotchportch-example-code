package com.keepthinker.example.general.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es= Executors.newCachedThreadPool();
		List<Worker> workers = new ArrayList<>();
		for(int i = 0; i < 10; i++)
			workers.add(new Worker(i));

		List<Future<String>> fus = es.invokeAll(workers);

		Thread.sleep(100);
		
		for(Future<String> fu : fus){
			System.out.println(fu.get());
		}
		

	}


	private static class Worker implements Callable<String>{

		private int id;
		public Worker(int id){
			this.id = id;
		}

		@Override
		public String call() throws Exception {

			System.out.println("worker " + id + " to run");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return String.valueOf(id);
		}
	}
}
