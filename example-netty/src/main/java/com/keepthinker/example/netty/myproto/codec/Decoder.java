package com.keepthinker.example.netty.myproto.codec;

import com.keepthinker.example.netty.myproto.buffer.ContentType;
import com.keepthinker.example.netty.myproto.buffer.Message;
import com.keepthinker.example.netty.myproto.buffer.Method;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Leave alone readable byte problems.<br/>
 * Solution for above question: you may replace ByteToMessageDecoder with ReplayingDecoder
 * @author keshengkai
 *
 */
public class Decoder extends ByteToMessageDecoder {
	
	private String name;
	public Decoder(String name){
		this.name = name;
	}


	//thread safe problems
	private Map<Channel, Message> messageMap = new ConcurrentHashMap<>();

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//		if (in.readableBytes() < 10) {
//			return;
//		}
		System.out.println(name + " is working in " + new Date().toString() + "  time: " + new Date().getTime());
		Channel channel = ctx.channel();
		Message message = messageMap.get(channel);
		if(message == null){
			message = new Message();
			messageMap.put(channel, message);
		}
		byte b = in.readByte();
		if(b == 0x0){
			message.setMethod(Method.REQUEST);
			
		}else if(b == 0x1){
			message.setMethod(Method.RESPONSE);
		}
		
		b = in.readByte();
		if(b == 0x0){
			message.setContentType(ContentType.INT);
			message.setLength(in.readInt());
			message.setPayload(in.readInt());
		}else if(b == 0x1){
			message.setContentType(ContentType.STRING);
			message.setLength(in.readInt());
			ByteBuf byteBuf = in.readBytes(message.getLength());
			byte[] bytes;
			if (byteBuf.hasArray()) {
			    bytes = byteBuf.array();
			} else {
			    bytes = new byte[message.getLength()];
			    byteBuf.getBytes(byteBuf.readerIndex(), bytes);
			}
			message.setPayload(new String(bytes));
		}
		
		out.add(message);
		System.out.println(name + " work finished and add message to out with message method: " + message.getMethod());
		
	}

}
