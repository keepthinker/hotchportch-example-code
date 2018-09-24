package com.keepthinker.example.general.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by keepthinker on 2018/3/11.
 */
public class CountdownLatchMain {

    public static void main(String[] args){
        ExecutorService es = Executors.newCachedThreadPool();

        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);
        for(int i = 0; i < size; i++) {
            es.execute(new KeyHolder(latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("latch free");

        es.shutdown();
    }

    private static class KeyHolder implements Runnable{
        private CountDownLatch latch;

        public KeyHolder(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("getting key");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }
}
