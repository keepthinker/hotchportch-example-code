package com.keepthinker.example.general.nio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by keepthinker on 2018/2/15.
 */
public class BufferMain {
    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put("12345678".getBytes(Charset.defaultCharset()));
        buffer.flip();

        int time = 2;
        byte[] strBytes = new byte[8];
        for(int i =0; i < time; i++){
            buffer.get(strBytes);
            System.out.println(new String(strBytes));
            buffer.rewind();
        }

    }
}
