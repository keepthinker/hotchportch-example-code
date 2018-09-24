package com.keepthinker.example.netty.udp.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.apache.log4j.Logger;

public class UdpTimeServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private final static Logger logger = Logger.getLogger(UdpTimeServerHandler.class);

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        System.out.println("active in upd server");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("channel read|" +  msg.toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        logger.info("channel read0|" +  msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}