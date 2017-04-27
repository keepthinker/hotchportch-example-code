package com.keepthinker.example.general.concurrency;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class InteruptedMain {
	public static void main(String[] args){
		MyThread t1 = new MyThread();
		t1.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.cancel();

	}

	private static class MyThread extends Thread{

		private BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>(10);

		@Override
		public void run() {
			while(true)
			{
				try {
					blockingDeque.put("a");

				} catch (InterruptedException e) {
					System.out.println(blockingDeque.size());
					e.printStackTrace();
					return;
				}
			}					
		}

		public void cancel(){
			this.interrupt();

		}
	}
}
