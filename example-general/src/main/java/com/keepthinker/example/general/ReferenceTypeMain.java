package com.keepthinker.example.general;

import java.lang.ref.*;
import java.lang.reflect.Field;

/**
 * Created by keepthinker on 2018/3/2.
 */
public class ReferenceTypeMain {
    public static void main(String[] args){
        strongReference();
        softReference();
        weakReference();
        phantomReference();
    }

    public static void strongReference(){
        System.out.println("strongReference start^^^^^^^^^^^^^^^^^");
        String str =  new String("StrongReference");
        System.gc();
        sleep(1);
        System.out.println("value after gc: " + str);
        System.out.println("strongReference end________________");
    }


    public static void softReference(){
        System.out.println("softReference start^^^^^^^^^^^^^^^^^");
        final ReferenceQueue<StringObject> referenceQueue = new ReferenceQueue<>();
        StringObject str =  new StringObject("SoftReference");
        SoftReference<StringObject> softReference = new SoftReference<>(str);
        str = null;
        System.out.println("softReference.isEnqueued() before gc: " + softReference.isEnqueued());;
        System.gc();
        sleep(1);
        System.out.println("softReference.isEnqueued() after gc: " + softReference.isEnqueued());;
        System.out.println("value after gc: " + softReference.get());
        new ReferenceQueueConsumer(referenceQueue).run();
        System.out.println("softReference end________________");
    }

    public static void weakReference(){
        System.out.println("weakReference start^^^^^^^^^^^^^^^^^");
        final ReferenceQueue<StringObject> referenceQueue = new ReferenceQueue<>();
        StringObject str =  new StringObject("WeakReference");
        StringWeakReference weakReference = new StringWeakReference(str, referenceQueue);
        weakReference.setName("StringWeakReference");
        str = null;
        System.out.println("weakReference.isEnqueued() before gc: " + weakReference.isEnqueued());
        System.gc();
        sleep(1);
        System.out.println("weakReference.isEnqueued() after gc: " + weakReference.isEnqueued());
        System.out.println("value after gc: " + weakReference.get());
        new ReferenceQueueConsumer(referenceQueue).run();
        System.out.println("weakReference end________________");
    }

    public static void phantomReference(){
        System.out.println("phantomReference start^^^^^^^^^^^^^^^^");
        StringObject str = new StringObject("phantomReference");
        final ReferenceQueue<StringObject> referenceQueue = new ReferenceQueue<>();
        final StringPhantomReference phantomReference = new StringPhantomReference(str, referenceQueue);
        str = null;
        System.out.println("phantomReference.isEnqueued() before gc: " + phantomReference.isEnqueued());
        System.gc();
        System.out.println("phantomReference.isEnqueued() after gc: " + phantomReference.isEnqueued());
        System.out.println("value after gc: " + phantomReference.get());
        new ReferenceQueueConsumer(referenceQueue).run();
        System.out.println("phantomReference end________________");
    }

    private static class ReferenceQueueConsumer extends Thread{
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
                        StringObject value = (StringObject) field.get(reference);
                        if (reference instanceof StringWeakReference) {
                            StringWeakReference stringWeakReference = (StringWeakReference)(reference);
                            System.out.println("StringWeakReference.name: " + stringWeakReference.getName() + "  |  ");
                        }
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

    private static class StringPhantomReference extends PhantomReference<StringObject>{

        public StringPhantomReference(StringObject referent, ReferenceQueue<? super StringObject> q) {
            super(referent, q);
        }

    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class StringObject{
        private String str;

        public StringObject(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("StringObject{");
            sb.append("str='").append(str).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    private static class StringWeakReference extends WeakReference{

        private String name;

        public StringWeakReference(Object referent) {
            super(referent);
        }

        public StringWeakReference(Object referent, ReferenceQueue queue) {
            super(referent, queue);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
