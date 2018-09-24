package com.keepthinker.example.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.apache.log4j.Logger;

public class UdpTimeDecoder extends SimpleChannelInboundHandler<DatagramPacket> {

    private final static Logger logger = Logger.getLogger(UdpTimeDecoder.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        System.out.println("channelRead0 decode bytes: " + msg);
        Long data = msg.content().getLong(0);
        System.out.println("channelRead0 decode obj: " + msg.content().getLong(0));
        ctx.fireChannelRead(data);
    }
/*
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead decode bytes: " + msg);
        System.out.println("channelRead decode obj: " + msg);
    }*/
}