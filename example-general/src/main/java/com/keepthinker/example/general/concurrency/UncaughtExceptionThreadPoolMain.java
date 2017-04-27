package com.keepthinker.example.general.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.atomic.AtomicInteger;

public class UncaughtExceptionThreadPoolMain {

	public static void main(String[] args) {
		ExecutorService executor = new ThreadPoolExecutor(10,
				15,
				10l,
				TimeUnit.MINUTES,
				new LinkedBlockingQueue<Runnable>(), new MyThreadFactory(),
				new AbortPolicy());

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
		executor.execute(t1);
		executor.shutdown();
	}


}

class UncaughtExceptionLogHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.format("Thread: %s, exception: %s", t.toString(), e.getMessage().toString());
	}

}

class MyThreadFactory implements ThreadFactory {
	private static final AtomicInteger poolNumber = new AtomicInteger(1);
	private final ThreadGroup group;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;

	public MyThreadFactory() {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() :
			Thread.currentThread().getThreadGroup();
		namePrefix = "pool-" +
				poolNumber.getAndIncrement() +
				"-thread-";
	}

	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r,
				namePrefix + threadNumber.getAndIncrement(),
				0);
		t.setUncaughtExceptionHandler(new UncaughtExceptionLogHandler());
		if (t.isDaemon())
			t.setDaemon(false);
		if (t.getPriority() != Thread.NORM_PRIORITY)
			t.setPriority(Thread.NORM_PRIORITY);
		return t;
	}
}
