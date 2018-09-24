package com.keepthinker.example.general.concurrency;

public class ThreadJoinMain {

	public static void main(String[] args) throws InterruptedException {

		final Thread t1 = new Thread(new Worker(0));
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("notify");
				t1.interrupt();

			}
		});
		t2.start();
		t1.start();
		long start = System.currentTimeMillis();
		t1.join(1500);
		long end  = System.currentTimeMillis();
		System.out.println("end --- time passed: " + (end - start));


	}

	private static class Worker implements Runnable{

		private int id;
		public Worker(int id){
			this.id = id;
		}

		@Override
		public void run()  {

			System.out.println("worker " + id + " to run");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
