package com.keepthinker.example.general;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * weak reference, strong reference
 * Created by keepthinker on 2017/5/10.
 */
public class ReferenceMain {

    private static WeakHashMap weakHashMap = new WeakHashMap();

    private static HashMap hashMap = new HashMap<>();

    public static void main(String[] args){
        testWeakHashMap();
        testWeakReference();
        testSoftReference();

    }

    private static void testWeakReference(){
        String obj = new String("hello");
        WeakReference<String> weakReference = new WeakReference<String>(obj);
        obj = null;
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("weakReference|" + weakReference.get());

    }

    private static void testSoftReference(){
        String obj = new String("hello2");
        SoftReference<String> weakReference = new SoftReference<String>(obj);
        obj = null;
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("weakReference|" + weakReference.get());

    }

    private static void testWeakHashMap(){

        String a = new String("key1");
        String b = new String("key2");
        weakHashMap.put(a, "value1");
        hashMap.put(b, "value2");
        a = null;
        b = null;
        System.gc();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("put key1 to null, but don't  remove it from weakHashMap |"
                                + weakHashMap.get("key1"));

                        System.out.println("put key2 to null, but don't  remove it from hashMap |"
                                + hashMap.get("key2"));
                    }
                }
        ).start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
