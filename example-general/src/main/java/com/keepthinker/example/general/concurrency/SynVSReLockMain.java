package com.keepthinker.example.general.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class SynVSReLockMain {
	
	private ReentrantLock lock = new ReentrantLock();
	
	private int val = 0;

	public synchronized void print(){
		val++;
		System.out.println("print " + val);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void print1(){
		lock.lock();
		val++;
		System.out.println("print " + val);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	

	public static class Worker implements Runnable {
		private SynVSReLockMain obj;
		
		public Worker(SynVSReLockMain obj){
			this.obj = obj;
		}

		@Override
		public void run() {
			obj.print();
		}
	}
	public static class Worker2 implements Runnable {
		private SynVSReLockMain obj;
		
		public Worker2(SynVSReLockMain obj){
			this.obj = obj;
		}

		@Override
		public void run() {
			obj.print1();
		}
	}
	
	private static ExecutorService ex = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws InterruptedException, ExecutionException, BrokenBarrierException{
		SynVSReLockMain main = new SynVSReLockMain();
		ex.execute(new Worker(main));
		ex.execute(new Worker2(main));

		ex.execute(new Worker(main));
		ex.shutdown();
	}
}

