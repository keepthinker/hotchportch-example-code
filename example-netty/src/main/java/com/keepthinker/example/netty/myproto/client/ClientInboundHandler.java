package com.keepthinker.example.netty.myproto.client;

import com.keepthinker.example.netty.myproto.buffer.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.List;

public class ClientInboundHandler  extends ChannelInboundHandlerAdapter {
	
	private List<Worker> workers;
	
	public ClientInboundHandler(List<Worker> workers){
		this.workers = workers;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client active in " + new Date().toString() + "  time: " + new Date().getTime());
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		Message message = (Message)msg;
		System.out.format("Client receive message with method: %s, payload: %s\n", message.getMethod(), message.getPayload());
		for(Worker worker : workers){
			worker.handleResponse(message);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}