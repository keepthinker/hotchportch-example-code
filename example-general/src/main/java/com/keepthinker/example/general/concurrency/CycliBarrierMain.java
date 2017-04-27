package com.keepthinker.example.general.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

public class CycliBarrierMain {
	private final int SIZE = 10;

	private final CyclicBarrier barrier = new CyclicBarrier(SIZE, new Runnable() {
		@Override
		public void run() {
			System.out.println("Thread ID: " + Thread.currentThread().getId());
		}
	});
	
	private List<Worker> workers;
	
	public CycliBarrierMain() {

		workers = new ArrayList<>();
		for(int i = 0; i < SIZE; i++){
			workers.add(new Worker(i));
		}
	}
	
	
	public void run(){
		
		for(Worker worker : workers){
			System.out.println("worker " + worker.id + " start");
			worker.start();
		}
	}

	private class Worker extends Thread{
		
		private int id;
		public Worker(int id){
			this.id = id;
		}
		public void run(){
			
			System.out.println("worker " + id + " to run");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				CycliBarrierMain.this.barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException, BrokenBarrierException{
		
		new CycliBarrierMain().run();
		
	}
}
