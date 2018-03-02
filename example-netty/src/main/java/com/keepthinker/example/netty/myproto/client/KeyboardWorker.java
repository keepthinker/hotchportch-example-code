package com.keepthinker.example.netty.myproto.client;

import java.util.Scanner;

import com.keepthinker.example.netty.myproto.buffer.ContentType;
import com.keepthinker.example.netty.myproto.buffer.Message;
import com.keepthinker.example.netty.myproto.buffer.Method;

import io.netty.channel.Channel;

public class KeyboardWorker extends Worker{

	public KeyboardWorker(){
	}
	
	public KeyboardWorker(Channel channel) {
		super(channel);
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			if(channel.isActive() == false){
				System.out.println("The channel in KeyboardWorker is not working");
				break;
			}
			String input = scanner.nextLine();
			System.out.println("the input: " + input);
			Message msg = new Message();
			msg.setMethod(Method.REQUEST);
			msg.setContentType(ContentType.STRING);
			msg.setLength(input.getBytes().length);
			msg.setPayload(input);
			channel.writeAndFlush(msg);
		}
	}
	

	@Override
	protected void handleResponse(Message msg) {
		System.out.println("KeyboardWorker: The data from server: " + msg.getPayload());
	}

}
