package com.keepthinker.example.general.classloader;

import java.io.FileDescriptor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class ClassLoaderMain {

	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

	@SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception {
		System.out.println("-----------------------------------");
		System.out.println("ClassLoaderMain class loader parents : ");
		ClassLoader cl = ClassLoaderMain.class.getClassLoader();
		recursiveCallParentCL(cl);
		cl.loadClass("com.keepthinker.example.general.classloader.Lifecycle").newInstance();
		ClassLoader syscl = ClassLoader.getSystemClassLoader();
		System.out.println("System ClassLoader"+ " : " + syscl);
		ClassLoader thrcl = Thread.currentThread().getContextClassLoader();
		System.out.println("Context Classloader currentThread"+ " : " + thrcl);
		System.out.println("syscl == thrcl:" + (syscl == thrcl));
		System.out.println("syscl== cl:" + (syscl== cl));
		
		System.out.println("-----------------------------------");
		ClassLoader clInt = java.lang.Integer.class.getClassLoader();
		ClassLoader clStr = java.lang.String.class.getClassLoader();
		System.out.println("Integer Classloader: " + clInt);
		System.out.println("String Classloader: " + clStr);
		System.out.println("String Classloader equals Integer's Classloader: " + (clInt == clStr));

		ClassLoader clMain = java.lang.Integer.class.getClassLoader();
		ClassLoader clSystem =ClassLoader.getSystemClassLoader();
		System.out.println(clMain);
		System.out.println("ClassLoader.getSystemClassLoader: " + clSystem);
		System.out.println(clMain == clSystem);
		FileDescriptor descriptor = new FileDescriptor();
		System.out.println(descriptor.getClass().getClassLoader());
		System.out.println("-----------------load jar--------------------");
		loadJar();
		System.out.println("-----------------load class--------------------");
//		loadClass();
		loadReadFileClass();
		loadSystemPropertyClass();
	}
	public static void recursiveCallParentCL(ClassLoader cl){
		if(cl == null){
			System.out.println("null ClassLoader");
			return;
		}
		recursiveCallParentCL(cl.getParent());
		System.out.println(cl);
	}

	public static void loadJar() throws Exception {
		String jarPath = basePath + "../../lib/console.jar";
		String binPath = basePath + "../../lib-bin/";
		URL[] urls = new URL[]{new URL("file:" + binPath)};

//		ClassLoader parent = ClassLoaderMain.class.getClassLoader();
		URLClassLoader cl = new URLClassLoader(urls, null);//, ClassLoaderMain.class.getClassLoader());
		System.out.println("class loader: " + cl);
		String classStr = "com.keepthinker.learn.BinConsole";
		Class<?> clazz = cl.loadClass(classStr);
		Object obj = clazz.newInstance();
		System.out.println("com.keepthinker.learn.BinConsole print :");
		clazz.getMethod("print").invoke(obj);

		urls = new URL[]{new URL("file:" + jarPath)};
		cl = new URLClassLoader(urls, cl);
		System.out.println("class loader: " + cl);
		String classStr2 = "com.keepthinker.learn.JarConsole";
		System.out.println("com.keepthinker.learn.JarConsole static{} :");
		Class.forName(classStr2, true, cl);
		

		System.out.println("-------------------parent--start----------------");
		System.out.println("URLClassLoader class loader parents : ");
		recursiveCallParentCL(cl);
		System.out.println("-------------------parent--end----------------");
	}

	public static void loadClass() throws Exception {
		URL[] url = new URL[0];
		ClassLoader cl = new URLClassLoader(url);
		System.out.println(cl.getResource("").toURI().getPath());

		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();
		String classStr = "com.keepthinker.example.general.classloader.Lifecycle";
		switch(choice){
		case "1":
			cl.loadClass(classStr);break;
		case "2":
			Class<?> clazz = cl.loadClass(classStr);
			clazz.newInstance();
		case "3":
			Class.forName(classStr);
		}
		scanner.close();
		System.out.println("-------------------parent--start----------------");
		System.out.println("URLClassLoader class loader parents : ");
		recursiveCallParentCL(cl);
		System.out.println("-------------------parent--end----------------");
	}

	@SuppressWarnings("resource")
	public static void loadReadFileClass() throws Exception{
		String path = basePath + "../../lib-bin/";
		URL[] urls = new URL[]{new URL("file:" + path)};
		ClassLoader cl = new URLClassLoader(urls);
		Class<?> clazz = cl.loadClass("com.keepthinker.learn.general.ReadFile");
		Object readFile = clazz.newInstance();
		System.out.println(readFile);
		byte[] data = (byte[])clazz.getDeclaredMethod("read", String.class).invoke(readFile, "pom.xml");
		System.out.println(new String(data));
	}

	public static void loadSystemPropertyClass() throws Exception{
		System.out.println("enter loadSystemPropertyClass() ----------------------------");
		String path = basePath + "../../lib/SystemProperty.jar";
		URL[] urls = new URL[]{new URL("file:" + path)};
		ClassLoader cl = new URLClassLoader(urls);//default use ClassLoaderMain.class.getClassLoader() as parent class loader
		recursiveCallParentCL(cl);
		Class<?> clazz = cl.loadClass("com.keepthinker.learn.SystemProperty");
		Object sp = clazz.newInstance();
		System.out.println(sp);
		clazz.getDeclaredMethod("doStuff").invoke(sp);
		
		cl.loadClass("com.keepthinker.example.general.classloader.Lifecycle").newInstance();
		
	}

}
