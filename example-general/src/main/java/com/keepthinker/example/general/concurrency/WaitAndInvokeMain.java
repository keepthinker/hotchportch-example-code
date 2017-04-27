package com.keepthinker.example.general.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitAndInvokeMain {

	public static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new BlockingQueue<>(10);
		for(int i = 0; i < 20; i++){
			EXECUTOR_SERVICE.execute(new Consumer(queue));
		}

		for(int i = 0; i < 20; i++){
			EXECUTOR_SERVICE.execute(new Producer(queue));
		}
		EXECUTOR_SERVICE.shutdown();
	}

	private static class Producer implements Runnable{
		private BlockingQueue<String> queue;

		public Producer(BlockingQueue<String> queue){
			this.queue = queue;
		}

		@Override
		public void run(){
			try {
				queue.put("product");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class Consumer implements Runnable{
		private BlockingQueue<String> queue;

		public Consumer(BlockingQueue<String> queue){
			this.queue = queue;
		}

		@Override
		public void run(){
			try {
				String result = queue.take();
				System.out.println(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class BlockingQueue<T>{
		private final int SIZE;


		private final T[] list;

		private int head = 0;
		private int tail = 1;

		@SuppressWarnings("unchecked")
		public BlockingQueue(int size){
			this.SIZE = size + 1;
			list = (T[]) new Object[size + 1];
		}

		public synchronized void put(T Obj) throws InterruptedException{
			while(isFull()){
				wait();
			}
			boolean isEmptyBefore = isEmpty();
			list[tail] = Obj;
			tail = (tail + 1) % SIZE;
			if(isEmptyBefore){
				System.out.println("notify all");
				notifyAll();
			}
		}

		public synchronized T take() throws InterruptedException{
			while(isEmpty())
				wait();
			boolean isFullBefore = isFull();
			int resultPos = (head + 1) % SIZE;
			T result = list[resultPos];
			list[resultPos] = null;
			head = resultPos;
			if(isFullBefore){
				System.out.println("notify all");
				notifyAll();
			}
			return result;
		}

		private synchronized boolean isEmpty(){
			int delta = tail - head;
			return (delta == 1 || delta == (1 - SIZE))? true : false;
		}

		private synchronized boolean isFull(){
			int delta = tail - head;
			return (delta == -1 || delta == SIZE - 1)? true: false;
		}
	}


}
