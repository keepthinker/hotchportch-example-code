package com.keepthinker.example.general.concurrency.utils;

/**
 * token bucket algorithm.<br/>
 * a tool class to limit the number of transaction per second.
 * Created by keepthinker on 2017/4/30.
 */
public class RateController {

    private int capacity;

    private int currentSize = 0;

    /** milliseconds. Must not be less than 100 due to the time cost of sleep thread's waking up */
    private int clearInterval = 200;

    private int tps;


    public static RateControllerBuilder newBuilder(){
        return new RateController.RateControllerBuilder();
    }

    public static class RateControllerBuilder{
        private RateController rateController;
        RateControllerBuilder(){
            rateController = new RateController();
        }

        public RateControllerBuilder setClearInterval(int checkInterval) {
            rateController.clearInterval = checkInterval;
            return this;
        }

        public RateControllerBuilder setTps(int tps) {
            rateController.tps = tps;
            return this;
        }

        public RateController build(){
            rateController.capacity = rateController.tps * rateController.clearInterval /1000;
            if(rateController.capacity == 0 || rateController.clearInterval < 100){
                throw new RuntimeException("tps and clearInterval combination is invalid " +
                        "or clearInterval < 100 ");
            }
            System.out.println("capacity:" + rateController.capacity);
            rateController.startClearBucketTask();
            return rateController;
        }
    }

    public synchronized void acquire(){
        currentSize++;
        if(currentSize > capacity){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void release(){
        currentSize = 1;
        notifyAll();
    }


    private void startClearBucketTask(){
        new Thread(new ClearBucketTask(this)).start();
    }

    private static class ClearBucketTask implements Runnable{
        private RateController rateController;
        ClearBucketTask(RateController rateController){
            this.rateController = rateController;
        }
        private long startClearTime;

        private long count = 0;

        @Override
        public void run() {
            startClearTime = System.currentTimeMillis();
            for (;;) {
                long delta = 0;
                if (count != 0) {  //eliminate sleep time incorrectness
                    delta = System.currentTimeMillis() - count * rateController.clearInterval - startClearTime;
                }
                long sleepTime;
                if (delta < rateController.clearInterval) {
                    sleepTime = rateController.clearInterval - delta;
                } else {
                    sleepTime = rateController.clearInterval;
                }

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rateController.release();
                count++;
            }
        }
    }

}
