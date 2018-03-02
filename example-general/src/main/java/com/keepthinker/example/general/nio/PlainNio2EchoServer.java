package com.keepthinker.example.general.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by keepthinker on 2018/2/14.
 */
public class PlainNio2EchoServer {

    public static void main(String[] args) throws IOException {
        new PlainNio2EchoServer().serve(8088);
    }

    public void serve(int port) throws IOException {
        System.out.println("Listening for connections on port " + port);
        final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(port);
        serverChannel.bind(address);
        final CountDownLatch latch = new CountDownLatch(1);
        serverChannel.accept(null, new
                CompletionHandler<AsynchronousSocketChannel, Object>() {
                    @Override
                    public void completed(final AsynchronousSocketChannel channel,
                                          Object attachment) {
                        serverChannel.accept(null, this);
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        channel.read(buffer, buffer,
                                new EchoCompletionHandler(channel));

                    }
                    @Override
                    public void failed(Throwable throwable, Object attachment) {
                        try {
                            serverChannel.close();
                        } catch (IOException e) {
// ingnore on close
                        } finally {
                            latch.countDown();
                        }
                    }
                });
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private final class EchoCompletionHandler implements
            CompletionHandler<Integer, ByteBuffer> {
        private final AsynchronousSocketChannel channel;
        EchoCompletionHandler(AsynchronousSocketChannel channel) {
            this.channel = channel;
        }
        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            buffer.flip();
            channel.write(buffer, buffer, new CompletionHandler<Integer,
                    ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {

                    buffer.flip();
                    byte[] data = new byte[buffer.limit()];
                    buffer.get(data);
                    System.out.println(new String(data));
                    buffer.rewind();

                    buffer.flip();

                    if (buffer.hasRemaining()) {
                        channel.write(buffer, buffer, this);
                    } else {
                        buffer.compact();
                        channel.read(buffer, buffer,
                                EchoCompletionHandler.this);
                    }
                }
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (IOException e) {
            // ingnore on close
                    }
                }
            });

        }
        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                channel.close();
            } catch (IOException e) {
            // ingnore on close
            }
        }
    }
}
