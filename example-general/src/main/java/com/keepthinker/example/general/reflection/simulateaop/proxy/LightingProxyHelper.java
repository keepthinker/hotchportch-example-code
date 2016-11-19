package com.keepthinker.example.general.reflection.simulateaop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LightingProxyHelper {
	@SuppressWarnings("unchecked")
	public static <T> T lightingProxy(final T targetObject, Class<T> advicedInterface){
		final LightingService lightingService = new LightingService();

		Object instance = Proxy.newProxyInstance(advicedInterface.getClassLoader(), new Class<?>[]{advicedInterface}, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				lightingService.light();
				method.invoke(targetObject, (Object[])null);
				return null;
			}
		}); 

		return (T) instance;
	}
}
