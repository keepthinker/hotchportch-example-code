package com.keepthinker.example.general.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by keepthinker on 2017/5/28.
 */
public class ConditionMain {

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();

    private Condition condition2 = lock.newCondition();

    private boolean isAvailable = true;
    public void lock(){
        try{
            lock.lock();
            if(isAvailable){
                try {
                    System.out.println("lock await");
                    condition1.await();
                    System.out.println("await1 end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("lock end");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void lock2(){
        try{
            lock.lock();
            if(isAvailable){
                try {
                    System.out.println("lock2 await");
                    condition2.await();
                    System.out.println("await2 end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("lock2 end");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void release1(){
        try{
            System.out.println("release1 enter");
            lock.lock();
            isAvailable = true;
            condition1.signalAll();
            System.out.println("signalAll 1");
            Thread.sleep(1000);
            System.out.println("release1 sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void release2(){
        try{
            System.out.println("release2 enter");
            lock.lock();
            isAvailable = true;
            condition2.signalAll();
            System.out.println("signalAll 2");
            Thread.sleep(1000);
            System.out.println("release2 sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        final  ConditionMain object = new ConditionMain();
        new Thread(new Runnable() {
            @Override
            public void run() {
                object.lock();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                object.lock2();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                object.release1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                object.release2();
            }
        }).start();

    }
}
