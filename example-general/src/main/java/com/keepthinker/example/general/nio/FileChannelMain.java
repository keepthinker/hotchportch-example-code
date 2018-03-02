package com.keepthinker.example.general.nio;

import com.keepthinker.example.general.util.Utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by keepthinker on 2018/2/15.
 */
public class FileChannelMain {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile( Utils.getContextClasspath() + "log4j.properties", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.rewind();
            System.out.println("\n-------------reprint");

            byte[] data = new byte[buf.limit()];
            while(buf.hasRemaining()){
                buf.get(data);
                System.out.print(new String(data));
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
