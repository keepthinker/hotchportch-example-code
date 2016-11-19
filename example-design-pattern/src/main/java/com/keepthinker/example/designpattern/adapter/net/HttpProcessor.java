package com.keepthinker.example.designpattern.adapter.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpProcessor implements Networking{

	ServerSocket server;
	InputStream is;
	OutputStream os;

	public HttpProcessor() {
		try {
			server = new ServerSocket(8000);
			Socket socket = server.accept();	
			is = socket.getInputStream();
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte read() {
		try {
			return (byte) is.read();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] buf = new byte[10];
	private int len = 10;
	private int pos = 0;
	private int remain = 10;
	@Override
	public String readStr(){
		try{
			while(true){
				int slen = is.read(buf, pos, remain);
				if(slen == remain){
					pos += slen;
					int newLen = len * 2;
					byte[] newBuf = new byte[newLen];
					System.arraycopy(buf, 0, newBuf, 0, len);
					buf = newBuf;
					remain = len;
					len = newLen;
				}else if(slen == 0 || slen == -1){
					break;
				}else{
					pos += slen;
					int newLen = len + slen;
					byte[] newBuf = new byte[newLen];
					System.arraycopy(buf, 0, newBuf, 0, len);
					buf = newBuf;
					remain = 0;
					len = newLen;
					break;
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		trim();
		return new String(buf, 0, len);
	}
	
	public void trim(){
		for(int i = len - 1; i > 0; i--){
			if(buf[i] > 0){
				len = i + 1;
				break;
			}
		}
	}

	@Override
	public void write(byte b) {
		try {
			os.write(b);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeString(String str) {
		try {
			os.write(str.getBytes());
			os.flush();
			os.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
