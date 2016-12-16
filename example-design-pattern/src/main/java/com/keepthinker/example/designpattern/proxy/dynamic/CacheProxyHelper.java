package com.keepthinker.example.designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class CacheProxyHelper {
	private static CacheProxyHelper helper = new CacheProxyHelper();

	public static <T> T getProxyInstance(final T targetObject, Class<T> advicedInterface, int period){
		helper.period = period;
		return helper.cacheProxy(targetObject, advicedInterface);
	}

	public static <T> T getProxyInstance(final T targetObject, Class<T> advicedInterface){
		return getProxyInstance(targetObject, advicedInterface, 10);
	}

	private Map<String, Object> cache = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	public <T> T cacheProxy(final T targetObject, Class<T> advicedInterface){

		Object instance = Proxy.newProxyInstance(advicedInterface.getClassLoader(), new Class<?>[]{advicedInterface}, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				String keyword = (String)args[0];
				if(cache.containsKey(keyword)){
					return (String) cache.get((String)args[0]);
				}
				String value = (String)method.invoke(targetObject, keyword);
				cache.put(keyword, value);
				return value;
			}
		}); 
		return (T) instance;
	}

	public CacheProxyHelper(){
		this.period = 10;
		clearingDeamon();
	}

	/**
	 * 秒数
	 * @param period
	 */
	public CacheProxyHelper(int period){
		this.period = period;
		clearingDeamon();
	}

	private int period;

	public void setPeriod(int period) {
		this.period = period;
	}

	private void clearingDeamon(){
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cache.clear();
				System.out.println("Cache2 is cleared");
			}
		};
		timer.schedule(task, 0, 1000 * period);
	}

}
