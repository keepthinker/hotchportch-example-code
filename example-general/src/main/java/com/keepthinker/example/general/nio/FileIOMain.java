package com.keepthinker.example.general.nio;

import com.keepthinker.example.general.util.Utils;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by keepthinker on 2018/2/20.
 */
public class FileIOMain {

    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile(Utils.getContextClasspath() + "log4j.properties", "rw");
        FileChannel channel = file.getChannel();

        long fileSize = file.length();

        int batch = 15;
        long sentinel = fileSize - batch;
        for(int i = 0; i < fileSize; i += batch) {

            long bufferSize = batch;
            if(i > sentinel){
                bufferSize = fileSize - i;
            }

            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, i, bufferSize);
            System.out.println("-----buffer size: " + buffer.limit());
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            System.out.println(new String(bytes));
        }

    }
}
