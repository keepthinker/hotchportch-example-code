package com.keepthinker.example.netty.myproto.server;

import com.keepthinker.example.netty.myproto.buffer.Message;
import com.keepthinker.example.netty.myproto.buffer.Method;
import com.keepthinker.example.netty.myproto.utils.Utils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ServerInboundHandler  extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(final ChannelHandlerContext ctx) { // (1)
    	System.out.println("server active in " + new Date().toString() + "  time: " + new Date().getTime());
	}
	

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Message message = (Message)msg;
		System.out.println("Client address: " + Utils.getLocalAddress(ctx.channel()));
		System.out.println("Server receive message whose method is " + message.getMethod());
		System.out.println("try to response with the same content");
		message.setMethod(Method.RESPONSE);
		ctx.writeAndFlush(message);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}