package com.keepthinker.example.netty.udp.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * fix the problem of fragmentation
 * @author keshengkai
 *
 */
public class UdpTimeClientHandler extends ChannelInboundHandlerAdapter {


	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("client read|" +  msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}