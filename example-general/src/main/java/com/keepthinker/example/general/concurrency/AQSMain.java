package com.keepthinker.example.general.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by keepthinker on 2017/5/29.
 */
public class AQSMain {

    public static class TwoLatch {

        private Sync sync = new Sync();

        public void release(){
            System.out.println("releaseShared");
            sync.releaseShared(0);
        }

        public void await(){
            try {
                sync.acquireSharedInterruptibly(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private static class Sync extends AbstractQueuedSynchronizer {

            public Sync(){
                setState(-2);
            }


            @Override
            protected int tryAcquireShared(int arg) {
                return getState();

            }

            @Override
            protected boolean tryReleaseShared(int arg) {
                while(true) {
                    int currentVal = getState();
                    int updatedVal = currentVal + 1;
                    System.out.println("updatedVal state " + updatedVal);
                    if (compareAndSetState(currentVal, updatedVal)) {
                        System.out.println("tryReleaseShared");
                        if(updatedVal ==0) {
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        final TwoLatch oneLatch = new TwoLatch();

        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("release1");
                oneLatch.release();
            }
        }).start();

        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("release2");
                oneLatch.release();
            }
        }).start();

        System.out.println("await");
        oneLatch.await();

        System.out.println("free");

        new CountDownLatch(1);
        new Semaphore(1);
    }
}
