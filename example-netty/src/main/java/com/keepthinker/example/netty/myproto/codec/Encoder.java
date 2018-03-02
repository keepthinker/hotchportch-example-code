package com.keepthinker.example.netty.myproto.codec;

import com.keepthinker.example.netty.myproto.buffer.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Date;

public class Encoder extends MessageToByteEncoder<Message>{

	private String name;
	public Encoder(String name){
		this.name = name;
	}
	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
		System.out.println(name + " is working in " + new Date().toString() + "  time: " + new Date().getTime());
		out.writeByte(msg.getMethod().ordinal());
		out.writeByte(msg.getContentType().ordinal());
		switch(msg.getContentType()){
		case INT: 
			out.writeInt(msg.getLength());
			out.writeInt((Integer)msg.getPayload());
			break;
		case STRING: 
			byte[] data = ((String)msg.getPayload()).getBytes();
			out.writeInt(data.length);
			out.writeBytes(data);
			break;
		default: throw new RuntimeException("ContentType error");
		}
		System.out.println(name + " complete its job");
		
	}

}
