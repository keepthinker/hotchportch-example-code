package com.keepthinker.example.general.reflection.simulateioc;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Container {
	private HashMap<String, Object> componentCache = new HashMap<>();

	public String scanPath = "com.keepthinker.example.general.reflection.simulateioc";
	

	public Object getObject(String className){
		return componentCache.get(className);
	};

	public Container() throws Exception{
		init();
	}

	private void init() throws Exception{
		scanClassAndCacheInstance();
		resolveDependencies();
	}

	private void scanClassAndCacheInstance() throws Exception{
		String path = getScanPath("com.keepthinker.example.general.reflection.simulateioc");
		//经过分析path目录发现类
		File file = new File(path);
		for(String fileName : file.list()){
			if(fileName.endsWith(".class")){
				String classname = fileName.replaceAll("\\.class", "");
				Class<?> clazz = Class.forName(scanPath + "." + classname);
				if(clazz.getAnnotation(Component.class) != null){
					String objectName = classname.substring(0,1).toLowerCase()
							+ classname.substring(1);
					componentCache.put(objectName, clazz.newInstance());
					System.out.println("Cache a instance : " + objectName);

				}
			}
		}
	}

	private String getScanPath(String subPath){
		String[] subPaths = subPath.split("\\.");
		StringBuilder builder = new StringBuilder(classpath());
		for(int i = 0, len = subPaths.length; i < len; i++){
			builder.append(subPaths[i]).append("/");
		}
		return builder.toString();
	}

	private String classpath(){
		return Thread.currentThread().getContextClassLoader().getResource("").getPath();
	}

	private void resolveDependencies(){
		try{
			for(Object obj : componentCache.values()){
				for(Field field : obj.getClass().getDeclaredFields()){
					if(false == fileldInjection(obj, field)){
						throw new RuntimeException(field.getName() + " Autowired failure");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean fileldInjection(Object obj, Field field){
		if(field.getAnnotation(Autowired.class) != null){
			field.setAccessible(true);
			for(Object ins : componentCache.values()){
				if(ins.getClass() == field.getType()){
					try {
						field.set(obj, ins);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		return true;
	}
}
