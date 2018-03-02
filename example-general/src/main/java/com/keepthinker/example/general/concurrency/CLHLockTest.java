package com.keepthinker.example.general.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by keepthinker on 2017/12/25.
 */
public class CLHLockTest {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService es = Executors.newCachedThreadPool();
        final ExecutorService esInside = Executors.newCachedThreadPool();
        final CLHLock lock = new CLHLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for(int i = 0; i < 10; i++){
                    try {
                        Thread.sleep(random.nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    esInside.execute(new CLHTask(lock, i));
                }
            }
        };
        es.execute(runnable);
        es.execute(runnable);
        Thread.sleep(3000);
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);
        esInside.shutdown();
        esInside.awaitTermination(1, TimeUnit.MINUTES);
    }


    private static class CLHTask implements Runnable{

        private CLHLock lock;
        private int id;

        public CLHTask(CLHLock lock, int id) {
            this.lock = lock;
            this.id = id;
        }
        @Override
        public void run() {
            lock.lock();
            System.out.println("enter critical area " + id);
            try {
                System.out.println("sleep " + id);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exit critical area " + id);
            lock.release();
        }
    }
}
