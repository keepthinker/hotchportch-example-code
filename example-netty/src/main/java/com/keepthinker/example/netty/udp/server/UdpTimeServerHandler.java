package com.keepthinker.example.netty.udp.server;

import com.keepthinker.example.netty.udp.UdpPayload;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.apache.log4j.Logger;

public class UdpTimeServerHandler extends SimpleChannelInboundHandler<UdpPayload> {

    private final static Logger logger = Logger.getLogger(UdpTimeServerHandler.class);

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        System.out.println("active in upd server");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UdpPayload udpPayload = (UdpPayload) msg;
        logger.info("channel read|" +  udpPayload);


        logger.info("channel write|" +  udpPayload);
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(8);
        byteBuf.writeLong(udpPayload.getData());
        ctx.writeAndFlush(new DatagramPacket(byteBuf, udpPayload.getAddr()));


    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UdpPayload msg) throws Exception {
        // won't go here
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}