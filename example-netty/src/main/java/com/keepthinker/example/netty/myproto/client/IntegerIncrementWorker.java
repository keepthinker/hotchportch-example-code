package com.keepthinker.example.netty.myproto.client;

import com.keepthinker.example.netty.myproto.buffer.ContentType;
import com.keepthinker.example.netty.myproto.buffer.Message;
import com.keepthinker.example.netty.myproto.buffer.Method;

import io.netty.channel.Channel;

public class IntegerIncrementWorker extends Worker{

	public IntegerIncrementWorker() {
	}
	public IntegerIncrementWorker(Channel channel) {
		super(channel);
	}
	
	private int count = 0;

	@Override
	public void run() {
		for(;;){
			System.out.println("IntegerIncrementWorker is working");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(channel.isActive() == false){
				System.out.println("The channel in IntegerIncrementWorker is not working");
				continue;
			}
			Message msg = new Message();
			msg.setMethod(Method.REQUEST);
			msg.setContentType(ContentType.INT);
			msg.setLength(4);
			msg.setPayload(count++);
			channel.writeAndFlush(msg);
			System.out.println("Succeeded in sending request with data: " + msg.getPayload());
		}
	}

	@Override
	protected void handleResponse(Message msg) {
		System.out.println("IntegerIncrementWorker: The data from server: " + msg.getPayload());
	}
}
