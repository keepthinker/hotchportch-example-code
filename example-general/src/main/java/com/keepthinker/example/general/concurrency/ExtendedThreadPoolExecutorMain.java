package com.keepthinker.example.general.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ExtendedThreadPoolExecutorMain {
	public static void main(String[] args){
		
		ThreadPoolExecutor executor = new LoggedThreadPoolExecutor(5, 5, 5L, TimeUnit.SECONDS,
				new LinkedBlockingQueue());
		executor.execute(new Worker(1));
		executor.execute(new Worker(2));
		executor.execute(new Worker(3));
		
		executor.shutdown();
	}
	
	private static class Worker implements Runnable {

		private int id;
		public Worker(int id){
			this.id = id;
		}

		@Override
		public void run() {

			System.out.println("worker " + id + " to run");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


class LoggedThreadPoolExecutor extends ThreadPoolExecutor{

	private final ThreadLocal<Long> threadLocal = new ThreadLocal<>(); 
	
	private final AtomicLong totalTime = new AtomicLong();
	
	LoggedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) { 
		super.beforeExecute(t, r);
		System.out.println("before execute " + t.getId());
		threadLocal.set(System.currentTimeMillis());
	}
	@Override
	protected void afterExecute(Runnable r, Throwable t) { 
		long delta = System.currentTimeMillis() - threadLocal.get();
		totalTime.addAndGet(delta);
		System.out.format("duration time is %d miliseconds\n", delta);
		super.afterExecute(r, t);
	}
	@Override
	protected void terminated() { 
		System.out.format("total time is %d miliseconds\n", totalTime.get());
		super.terminated();
	}


}