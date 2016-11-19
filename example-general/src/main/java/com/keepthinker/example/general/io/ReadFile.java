package com.keepthinker.example.general.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class ReadFile {
	public byte[] read(String path) throws Exception{
		File file = new File(path);
		System.out.println(new Date(file.lastModified()));
		int size =  (int)file.length();
		byte[] bytes = new byte[size];
		InputStream is = new FileInputStream(file);
		is.read(bytes, 0, size);
		is.close();
		return bytes;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(new ReadFile().read("pom.xml"));
	}
}
