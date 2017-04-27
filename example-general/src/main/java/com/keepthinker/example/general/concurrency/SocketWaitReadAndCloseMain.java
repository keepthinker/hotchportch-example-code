package com.keepthinker.example.general.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketWaitReadAndCloseMain {
	static Socket socket;
	public static void main(String[] args){
		try {
			socket = new Socket("localhost", 10000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new MyThread(socket).start();
		try {
			InputStream in = socket.getInputStream();
			int b = in.read();
			System.out.println(b);
		} catch (IOException e) {
			System.out.println("failed to read");
			e.printStackTrace();
		}

	}
	
	public static class MyThread extends Thread{
		private Socket socket;
		public MyThread(Socket socket){
			this.socket = socket;
		}
		public void run(){
			try {
				Thread.sleep(1000);
				socket.close();
			} catch (Exception e) {
				System.out.println("error in MyThread");
				e.printStackTrace();
			}
		}
	}
}
