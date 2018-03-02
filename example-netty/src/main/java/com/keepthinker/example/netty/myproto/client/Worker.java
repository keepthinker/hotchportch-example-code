package com.keepthinker.example.netty.myproto.client;

import com.keepthinker.example.netty.myproto.buffer.Message;

import io.netty.channel.Channel;

public abstract class Worker implements Runnable{
	public Worker(){
	}
	
	protected Channel channel;
	
	public Worker(Channel channel) {
		this.channel = channel;
	}
	
	protected abstract void handleResponse(Message msg);
	
	public void setChannel(Channel channel){
		this.channel = channel;
	}
}
