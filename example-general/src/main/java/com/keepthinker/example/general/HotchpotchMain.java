package com.keepthinker.example.general;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author keshengkai
 */
public class HotchpotchMain {
	private static Logger logger = LoggerFactory.getLogger(HotchpotchMain.class);
	private static Lock lock = new ReentrantLock();
	private static final BlockingQueue queue = new LinkedBlockingQueue();
	public static void main(String[] args){
		System.out.println(URLEncoder.encode("文件名称.xlsx"));
		System.out.println(DurationFormatUtils.formatDuration(Duration.ofSeconds(3661).toMillis(), "H小时m分钟", true));
	}


	public static void atomicInteger(){
		AtomicInteger counter = new AtomicInteger(1);
		for (int i = 0; i < 10; i++) {
			counter.getAndUpdate(x -> x * 2);
			System.out.println(counter.get());
		}
		System.out.println(counter.get());
	}

	private static void foo1(){

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000L);
				} catch (InterruptedException e) {
					System.out.println("exception:" + e.getMessage());
				}
				System.out.println("running");
			}
		});

		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000L);
				} catch (InterruptedException e) {
					System.out.println("exception:" + e.getMessage());
				}
				System.out.println("running");
			}
		});

		threadPoolExecutor.shutdownNow();

//		foo();
		try {
			Thread.sleep(30000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void foo(){



		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					Object obj = null;
					try {
						obj = queue.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("run" + obj);
				}
			}
		});

		final ReferenceQueue<Thread> referenceQueue = new ReferenceQueue<>();
		final ThreadPhantomReference phantomReference = new ThreadPhantomReference(thread, referenceQueue);
		System.out.println("value before gc|" + phantomReference.get());
		queue.add("data");
		thread.start();
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread = null;
		queue.add("data");
		System.out.println("phantomReference.isEnqueued() before gc: " + phantomReference.isEnqueued());
		System.gc();
		System.out.println("phantomReference.isEnqueued() after gc: " + phantomReference.isEnqueued());
		System.out.println("value after gc: " + phantomReference.get());
		new ReferenceQueueConsumer(referenceQueue).run();
		System.out.println("phantomReference end________________");
	}
	private static class ReferenceQueueConsumer{
		ReferenceQueue referenceQueue = new ReferenceQueue<>();
		ReferenceQueueConsumer (ReferenceQueue referenceQueue ){
			this.referenceQueue = referenceQueue;
		}

		public void run(){
			for(int i = 0; i < 3; i++){
				Reference reference = referenceQueue.poll();
				if(reference == null){
					try {
						Thread.sleep(1000);
						System.out.println("sleep for 1s");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					try {
						Field field = Reference.class.getDeclaredField("referent");
						field.setAccessible(true);
						Thread value = (Thread) field.get(reference);
						System.out.println("ReferenceQueueConsumer|Reference's referent value:" + value);
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					reference.clear(); //.如果被从队列取出，再也没有被引用，无需调用此方法。
				}
			}}
	}

	private static class ThreadPhantomReference extends PhantomReference<Thread>{

		public ThreadPhantomReference(Thread referent, ReferenceQueue<? super Thread> q) {
			super(referent, q);
		}
	}


	public static void test5(){

		final ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		Object obj = new Object();
		TestWeakReference weakReference = new TestWeakReference(obj, referenceQueue);
		obj = null;
		System.gc();
		System.out.println(weakReference.get());

		TestWeakReference testWeakReference;
		for(int i = 0; i < 5; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			testWeakReference = (TestWeakReference) referenceQueue.poll();
			if(testWeakReference != null) {
				System.out.println(testWeakReference.getVal());
				try {
					Field field = null;
					field = Reference.class.getDeclaredField("referent");
					field.setAccessible(true);
					Object value =  field.get(testWeakReference);
					System.out.println("Reference's referent value:" + value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class TestWeakReference extends PhantomReference{//WeakReference {

		private String val = "test";

		public TestWeakReference(Object referent, ReferenceQueue queue) {
			super(referent, queue);
		}

		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}
	}

	public static void testCallable(){
		ExecutorService executorService = Executors.newCachedThreadPool();
		RequestTask requestTask = new RequestTask();
		Future<String> future = executorService.submit(requestTask);
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static class RequestTask implements Callable<String> {

		@Override
		public String call() throws Exception {

			return "";
		}
	}

	private static void test3(){
		final PrimitiveConObject primitiveConObject = new PrimitiveConObject();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				primitiveConObject.toWait();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				primitiveConObject.toNotify();
			}
		});
		t1.start();
		t2.start();


	}


	private static void test4(){

		final AqsConObject aqsConObject = new AqsConObject();

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				aqsConObject.toWait();
			}
		});

		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				aqsConObject.toNotify();
			}
		});
		t3.start();
		t4.start();
	}

	private static class PrimitiveConObject {

		private volatile boolean wakeup;

		public synchronized void toWait(){
			while(!wakeup){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("wakeup: " + wakeup);
		}

		public synchronized void toNotify(){
			wakeup = true;
			this.notify();
			System.out.println("notified: " + wakeup + " and sleep for 2s");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private static class AqsConObject {

		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();

		private volatile boolean wakeup;

		public void toWait(){
			lock.lock();
			try {
				while(!wakeup){
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("wakeup: " + wakeup);
			}finally {
				lock.unlock();
			}
		}

		public void toNotify(){
			lock.lock();
			try{
				wakeup = true;
				condition.signalAll();
				System.out.println("notified: " + wakeup + " and sleep for 2s");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}finally {
				lock.unlock();
			}
		}

	}


	private static void test1(){


		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					LockSupport.park(this);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t1");
			}
		});

		t1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LockSupport.unpark(t1);

	}

	private static void test2(){


		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					LockSupport.park(this);
					System.out.println("interrupted: " + Thread.interrupted());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t2");
			}
		});

		t1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.interrupt();

	}

}


