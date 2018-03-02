package com.keepthinker.example.netty;

import com.keepthinker.example.netty.time.TimeDecoder;
import com.keepthinker.example.netty.time.usepojo.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * Created by keepthinker on 2017/6/8.
 */
public class Main {
    @Test
    public void testBuf(){
        new Thread(new Runnable(){

            @Override
            public void run() {
                System.gc();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            for (int i = 0; i < 10000000; i++) {
                String dataSrc = "asdfasfasdfasdfasdfasdfasdfasdddddddddddddddddddddddddddddddddddd";
                ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.buffer(dataSrc.length());//Unpooled.directBuffer(dataSrc.length());//
                byteBuf.writeBytes(dataSrc.getBytes());
                if (i % 10000 == 0) {
                    System.out.println(i);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ByteBuf byteBuf1 = byteBuf.duplicate();
                byte[] data = new byte[byteBuf.readableBytes()];

                ByteBuf byteBuf3 = PooledByteBufAllocator.DEFAULT.buffer(dataSrc.length());
                byteBuf3.writeBytes(byteBuf1);
                byteBuf3.release();


//            System.out.println("readableBytes:" + byteBuf1.readableBytes());
//            System.out.println("writableBytes:" + byteBuf1.writableBytes());
//            System.out.println("refCnt:" + byteBuf1.refCnt());
//            System.out.println("readerIndex:" + byteBuf1.readerIndex());
//            System.out.println("writerIndex:" + byteBuf1.writerIndex());
//            byteBuf.release();
            }
        }catch(Exception e) {
            System.out.println("System gc");
            System.gc();
        }
    }

    @Test
    public void testEmbeded(){
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new TimeDecoder());
        UnixTime time = new UnixTime();
        System.out.println(time.value());
        embeddedChannel.writeInbound(time);
        UnixTime outTime =  embeddedChannel.readInbound();
        System.out.println(outTime.value());

        embeddedChannel.writeInbound(100);
        outTime =  embeddedChannel.readInbound();
        System.out.println(outTime.value());
    }

}
