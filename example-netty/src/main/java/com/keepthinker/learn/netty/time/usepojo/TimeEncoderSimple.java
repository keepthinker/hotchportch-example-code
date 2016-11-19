package com.keepthinker.example.netty.time.usepojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TimeEncoderSimple  extends MessageToByteEncoder<UnixTime> {
    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
    	System.out.println("time encoder simple is working");
        out.writeInt((int)msg.value());
    }
}