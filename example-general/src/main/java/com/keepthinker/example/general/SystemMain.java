package com.keepthinker.example.general;

import java.io.*;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class SystemMain {
	public static void main(String[] args) throws IOException{
		System.out.println(System.getProperty("PATH"));

		System.out.println("---------------------pro---------------------");
		
		Properties pros = System.getProperties();
		Set<Object> sortedSet = new TreeSet<Object>(pros.keySet());
		for(Object obj : sortedSet){
			System.out.printf("%35s : %s\n", obj, pros.getProperty((String)obj));
		}
		System.out.println("---------------------env---------------------");
		
		Map<String, String> maps = System.getenv();
		sortedSet = new TreeSet<Object>(maps.keySet());
		for(Object key : sortedSet){
			System.out.printf("%35s : %s\n", key, maps.get(key));
		}
		
		System.out.print((int)'\r'); //carrige return
		System.out.print((int)'\n'); //line feed
		System.out.println();
		URL uri = SystemMain.class.getClassLoader().getResource(".");
		String classpath = uri.getPath();
		File file = new File(classpath + "sys.out");
		if(file.exists() == false){
			file.createNewFile();
		}
		PrintStream ps = System.out;
		System.setOut(new PrintStream(new FileOutputStream(file)));
		System.out.println("system out print");
		
		System.setOut(ps);
		
		Runtime runtime = Runtime.getRuntime();
		BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec("netstat -an").getInputStream()));
		while(br.ready()){
			System.out.println(br.readLine());
		}
		NumberFormat numberFormat = NumberFormat.getInstance();
		System.out.println("totalMemory : " + numberFormat.format(runtime.totalMemory()));
		System.out.println("freeMemory : " + numberFormat.format(runtime.freeMemory()));
		System.out.println("maxMemory : " + numberFormat.format(runtime.maxMemory()));
		System.out.println("availableProcessors : " + numberFormat.format(runtime.availableProcessors()));
//		runtime.load("/home/avh/lib/libX11.so");
		System.out.println("end");
	}
	
}
