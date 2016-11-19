package com.keepthinker.example.designpattern.adapter;

import com.keepthinker.example.designpattern.adapter.data.DataProcessor;
import com.keepthinker.example.designpattern.adapter.net.HttpProcessor;


public class Main {
	public static void main(String[] args){
		HttpProcessor httpProcessor = new HttpProcessor();
		DataAdaptor dataAdaptor = new DataAdaptor(httpProcessor);
		
		DataProcessor dataProcessor = new DataProcessor(dataAdaptor);
		String str = dataProcessor.getData();
		System.out.println(str);
		
		String responseLine = "HTTP/1.1 200 OK\r\n"
				+ "Content-Type:text/plain; charset=UTF-8\r\n"
				+ "\r\n";
		httpProcessor.writeString(responseLine + str);
	}
}
