package com.keepthinker.example.designpattern.adapter.net;

public interface Networking {
	byte read();
	String readStr();
	void write(byte b);
	void writeString(String str);
}
