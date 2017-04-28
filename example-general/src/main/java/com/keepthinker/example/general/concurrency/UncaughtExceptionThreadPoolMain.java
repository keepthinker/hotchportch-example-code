package com.keepthinker.example.general.concurrency;

import com.keepthinker.example.general.concurrency.utils.ConcurrentUtils;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.atomic.AtomicInteger;

public class UncaughtExceptionThreadPoolMain {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("hello");
				throw new RuntimeException("error in this thread");
			}
		});
		ConcurrentUtils.execute(t1);
	}

}
