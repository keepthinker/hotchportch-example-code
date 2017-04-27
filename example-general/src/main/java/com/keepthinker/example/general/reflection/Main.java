package com.keepthinker.example.general.reflection;

import com.keepthinker.example.general.model.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

	public static void main(String[] args) throws Exception{
//		dynamicProxy();
		
		Person person = new Person();
		person.setBirthplace("   asdfa   a ");
		person.setId(1);
		person.setOthers(new String[]{" adfasf", "adadf "});
		BeanUtils.trim(person);
		System.out.println(person);
		
	}
	
	private static void dynamicProxy() throws Exception{

		Class<?> someInterface = Class.forName("com.keepthinker.example.general.reflection.Manage");
		Class<?> someInterface1 = Class.forName("com.keepthinker.example.general.reflection.Operation");

		Object instance = Proxy.newProxyInstance(someInterface.getClassLoader(), new Class<?>[]{someInterface, someInterface1}, new InvocationHandler() {

	        @Override
	        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

	            //Handle the invocations
	            if(method.getName().equals("get")){
	                return "get";
	            }else if(method.getName().equals("retrive")){
	            	return args[0];
	            }else{
	            	return null;
	            }
	        }
	    }); 
		System.out.println(instance.getClass().getDeclaredMethod("get", (Class<?>[])null).invoke(instance, (Object[])null));
	    System.out.println();
	    System.out.println(instance.getClass().getDeclaredMethod("retrive", int.class).invoke(instance, new Object[]{12}));
		
	    externalObjectInject(instance);
	    
	}
	
	public static void externalObjectInject(Object obj){
	    System.out.println();
		System.out.println(((Manage)obj).get());
	}

}

