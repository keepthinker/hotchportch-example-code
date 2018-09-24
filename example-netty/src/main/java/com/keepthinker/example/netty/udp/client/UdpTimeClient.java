package com.keepthinker.example.netty.udp.client;

import com.keepthinker.example.netty.udp.UdpTimeDecoder;
import com.keepthinker.example.netty.udp.UdpTimeEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class UdpTimeClient {
    private static final String DESC_HOST = "255.255.255.255";

    public static void main(String[] args) throws Exception {
        String host;
        int port;
        if(args.length > 0){
            host = args[0];
            port = Integer.parseInt(args[1]);
        }else{
//            host = "127.0.0.1";
            host = DESC_HOST;
            port = 18080;
        }
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioDatagramChannel.class);
            b.option(ChannelOption.SO_REUSEADDR, false);
            b.option(ChannelOption.SO_BROADCAST, true);

            b.handler(new ChannelInitializer<DatagramChannel>() {
                @Override
                public void initChannel(DatagramChannel ch) throws Exception {
                    ch.pipeline().addLast(new UdpTimeDecoder(), new UdpTimeEncoder(), new UdpTimeClientHandler());
                }
            });
            b.localAddress(21000);

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            new Thread(new TimeEchoClient(f.channel())).start();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    private static class TimeEchoClient implements Runnable {

        private Channel channel;
        public TimeEchoClient(Channel channel){
            this.channel = channel;
        }

        @Override
        public void run() {
            boolean loop = false;
            do{
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("client try to write time to channel");

                if(channel.isActive()) {
                    ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer(8);
                    System.out.println("client write time to channel");
                    buffer.writeLong(System.currentTimeMillis());
                    try {
                        channel.writeAndFlush(new DatagramPacket(buffer, new InetSocketAddress(InetAddress.getByName((DESC_HOST)), 18080)));
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }while(loop);

            channel.close();
        }
    }
}