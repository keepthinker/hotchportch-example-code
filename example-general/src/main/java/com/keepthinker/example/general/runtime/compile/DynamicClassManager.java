package com.keepthinker.example.general.runtime.compile;

import java.io.File;
import java.io.IOException;

/**
 * by default, all path is relative to classpath
 * @author keshengkai
 *
 */
public class DynamicClassManager {
	private ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
	
	private String classpath = contextClassLoader.getResource("").getPath();
	
	private String destClassDirPath = classpath;
	
	public String getDestClassPath() {
		return destClassDirPath;
	}

	public void setDestClassPath(String destClassDirPath) {
		this.destClassDirPath = destClassDirPath;
	}

	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	/**
	 *  relative to classpath
	 * @param srcJFilePath
	 */
	public void compile(String srcJFilePath){
		try {
			File file = new File(destClassDirPath);
			if(file.exists() == false){
				if(file.mkdirs() == false){
					throw new RuntimeException("file.mkdirs() failed");
				};
			}
			srcJFilePath = classpath + srcJFilePath;
			Process process = Runtime.getRuntime().exec("javac -d " + destClassDirPath + " " + srcJFilePath);
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException("Runtime.getRuntime().exec error");
		}
		
	}
	
	public Object newInstance(String className){
		try {
			Class<?> cl = contextClassLoader.loadClass(className);
			return cl.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
