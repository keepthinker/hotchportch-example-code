package com.keepthinker.example.general.nio;

import com.keepthinker.example.general.util.Utils;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by keepthinker on 2018/2/15.
 */
public class TransferMain {
    public static void main(String[] args) throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile(Utils.getContextClasspath() + "log4j.properties", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile(Utils.getContextClasspath() + "log4j-copy.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }
}
