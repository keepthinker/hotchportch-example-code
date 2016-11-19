package com.keepthinker.example.general.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class IOMain {
	public static void main(String[] args) throws Exception{
		writeFile();
	}
	
	public static void readFile() throws Exception{
		String absPath = IOMain.class.getClassLoader().getResource("log4j.properties").getPath();
		File file = new File(absPath);
		long len = file.length();
		if(len > Integer.MAX_VALUE){
			return;
		}
		InputStream in = new FileInputStream(file);
		byte[] content = new byte[(int) len];
		in.read(content, 0, (int)len);
		print(new String(content, "UTF-8"));
		in.close();
	}
	
	public static void writeFile() throws Exception{
		String absPath = "a.txt";
		File file = new File(absPath);
		OutputStream os = new FileOutputStream(file);
		String content = "Hello world";
		int len = content.getBytes().length;
		os.write(content.getBytes(), 0, (int)len);
		System.out.println(file.lastModified());
		os.close();
	}
	
	private static void print(Object obj){
		System.out.println(obj);
	}
}
