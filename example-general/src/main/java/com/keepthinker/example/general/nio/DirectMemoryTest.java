package com.keepthinker.example.general.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * Created by keepthinker on 2018/3/1.
 */
public class DirectMemoryTest {
    public static void main(String[] args){
        test1();
//        test2();
        test3();
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
                PooledByteBufAllocator.DEFAULT.directBuffer(10);  //direct memory leak
            }
            if(i % 100000 == 0){
                System.out.println(i);
            }
        }
        System.out.println(object);
    }

    private static void test3(){
        System.out.println("-----------netty direct byteBuf duplicate test----------");
        ByteBuf byteBuf = Unpooled.directBuffer(4);
        byteBuf.writeInt(10);

        ByteBuf byteBufDuplicate = byteBuf.duplicate().retain(); //retail gain 1 more refCnt
        System.out.println("byteBufDuplicate: " + byteBufDuplicate.readInt());
        byteBufDuplicate.release();
        System.out.println("origin byteBuf: " + byteBuf.readInt());
        byteBufDuplicate.release();// refCnt decrease to 0
        System.out.println("release two times, origin byteBuf: " + byteBuf.readInt());

    }

}
