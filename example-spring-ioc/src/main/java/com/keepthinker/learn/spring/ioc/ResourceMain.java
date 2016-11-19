package com.keepthinker.example.spring.ioc;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;

public class ResourceMain {
	public static void main(String[] args) throws IOException{
		urlResource();
		classpathResource();
		fileSystemResource();
		ByteArrayResource();
	}
	
	public static void urlResource() throws IOException{
		UrlResource urlResource = new UrlResource("http://192.168.2.234:2108/v2/deviceconfig/getsfinfo?model=vivo%20x5pro%20D&elapsedtime=65216548&imei=1456489411&productName=PD1503&sysver=funtouch&romver=as34312");
		printStream(urlResource.getInputStream());
				
	}
	
	public static void classpathResource() throws IOException{
		ClassPathResource resource = new ClassPathResource("config.properties");
		printStream(resource.getInputStream());
	}
	
	public static void fileSystemResource() throws IOException{
		FileSystemResource resource = new FileSystemResource("/home/keshengkai/workspace/example/example-spring-ioc/src/main/resources/applicationContext.xml");
		printStream(resource.getInputStream());
	}
	
	public static void ByteArrayResource() throws IOException{
		ByteArrayResource resource = new ByteArrayResource("String".getBytes());
		printStream(resource.getInputStream());
	}
	
	public static void printStream(InputStream is) throws IOException{
		InputStreamReader reader = new InputStreamReader(is);
		int c;
		while((c = reader.read()) != -1){
			System.out.print((char)c);
		}
		System.out.println();
	}
	
}
