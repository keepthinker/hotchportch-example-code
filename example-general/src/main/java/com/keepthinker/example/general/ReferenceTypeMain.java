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
        String str =  new String("StrongReference");
        System.gc();
        System.out.println(str);
    }


    public static void softReference(){
        String str =  new String("SoftReference");
        SoftReference<String> softReference = new SoftReference<String>(str);
        str = null;
        System.gc();
        System.out.println(softReference.get());
    }

    public static void weakReference(){
        String str =  new String("WeakReference");
        WeakReference<String> softReference = new WeakReference<String>(str);
        str = null;
        System.gc();
        System.out.println(softReference.get());
    }

    public static void phantomReference(){
        StringObject str = new StringObject("phantomReference");
        final ReferenceQueue<StringObject> referenceQueue = new ReferenceQueue<>();
        final StringPhantomReference phantomReference = new StringPhantomReference(str, referenceQueue);
        str = null;
        System.out.println("phantomReference enqueued before gc: " + phantomReference.isEnqueued());
        System.gc();
        System.out.println("phantomReference enqueued after gc: " + phantomReference.isEnqueued());

        new Thread(){
            public void run(){
                while(true){
                    Reference reference = referenceQueue.poll();
                    if(reference == null){
                        try {
                            Thread.sleep(1000);
                            System.out.println("sleep for 1s");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("enqueued: " + reference.isEnqueued());

                        try {
                            Field field = Reference.class.getDeclaredField("referent");
                            field.setAccessible(true);
                            StringObject value = (StringObject) field.get(reference);
                            System.out.println("Reference:" + value);
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        reference.clear(); //.如果被从队列取出，再也没有被引用，无需调用此方法。
                    }
                }}
        }.start();
    }

    private static class StringPhantomReference extends PhantomReference<StringObject>{


        public StringPhantomReference(StringObject referent, ReferenceQueue<? super StringObject> q) {
            super(referent, q);
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


}
