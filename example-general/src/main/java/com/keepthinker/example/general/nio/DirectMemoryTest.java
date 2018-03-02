package com.keepthinker.example.general.nio;

import io.netty.buffer.PooledByteBufAllocator;

import java.nio.ByteBuffer;

/**
 * Created by keepthinker on 2018/3/1.
 */
public class DirectMemoryTest {
    public static void main(String[] args){
        test1();
        test2();
    }

    private static void test1(){
        Object object = null;
        for(int i = 0; i < 50000000; i++) {
            object = new Object();
            if(i % 1000 == 0) {
                ByteBuffer.allocateDirect(10);
            }
            if(i % 100000 == 0){
                System.out.println(i);
            }
        }
        System.out.println(object);
    }

    private static void test2(){
        Object object = null;
        for(int i = 0; i < 50000000; i++) {
            object = new Object();
            if(i % 1000 == 0) {
                PooledByteBufAllocator.DEFAULT.directBuffer(10);
            }
            if(i % 100000 == 0){
                System.out.println(i);
            }
        }
        System.out.println(object);
    }
}
