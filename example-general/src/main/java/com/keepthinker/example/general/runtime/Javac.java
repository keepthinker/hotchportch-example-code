package com.keepthinker.example.general.runtime;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class Javac {

	public static void main(String[] args) {
		try {
			 Runtime.getRuntime().exec("javac D:\\Development\\workspace\\example\\example-general\\src\\main\\java\\com\\keepthinker\\example\\general\\SystemMain.java");
			File file = new File("D:\\Development\\workspace\\example\\example-general\\src\\main\\java\\");
			Process process = Runtime.getRuntime().exec("java com.keepthinker.example.general.SystemMain", null, file);
//			process.waitFor();
			URLClassLoader cl = new URLClassLoader(new URL[]{new URL("file:" + "D:\\Development\\workspace\\example\\example-general\\src\\main\\java\\")});
			System.out.println(cl.loadClass("com.keepthinker.example.general.SystemMain").newInstance());
			cl.close();
			InputStream in = process.getInputStream();
			printStreamSteadily(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printStreamSteadily(InputStream in) throws IOException{
		int bufferSize = 100000;
		byte b;
		byte[] data = new byte[bufferSize];
		int i = 0;
		while((b = (byte) in.read()) != -1){
			data[i++] = b;
		}
		if(i == 0){
			return;
		}
		System.out.println(new String(data, 0, i-1, "GBK"));
	}
	
	public static void printStream(InputStream in) throws IOException{
		int bufferSize = 1000;
		byte[] data = new byte[bufferSize];
		int vsize = in.read(data);
		if(vsize == -1){
			return;
		}
		System.out.println(new String(data, 0, vsize, "GBK"));
	}

}
