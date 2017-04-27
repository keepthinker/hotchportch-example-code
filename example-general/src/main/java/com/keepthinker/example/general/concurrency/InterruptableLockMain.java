package com.keepthinker.example.general.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptableLockMain {
	private Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		final InterruptableLockMain main = new InterruptableLockMain();

		class MyThread extends Thread{
			public void run(){
				main.longTask();
			}
		}
		
		Thread thread = new MyThread();
		thread.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
		thread = new MyThread();
		thread.start();;
	}
	

	private void longTask(){
		System.out.println("enter longTask");
		try {
			Thread.sleep(2000);
		}catch (InterruptedException e) {
			System.out.println("sleep interrupted: " + e.getMessage());
			Thread.currentThread().interrupt();
		}
		System.out.println("after sleep");
		try{
			lock.lockInterruptibly();
			System.out.println("after lockInterruptibly");
		}catch (InterruptedException e) {
			System.out.println("lockInterruptibly interrupted: " + e.getMessage());
		}finally{
			lock.unlock();
		}

	}


}
