package com.keepthinker.example.designpattern.proxy;

import com.keepthinker.example.designpattern.proxy.dynamic.CacheProxyHelper;

public class Main {
	public static void main(String[] args){
		Search search = new SearchServiceCacheProxy(7);
		System.out.println(search.search("a"));
		sleep(2);
		System.out.println(search.search("a"));
		sleep(2);
		System.out.println(search.search("b"));
		sleep(2);
		System.out.println(search.search("a"));
		sleep(2);
		System.out.println(search.search("b"));
		sleep(2);
		System.out.println(search.search("a"));
		sleep(2);
		
		Search search2 = CacheProxyHelper.getProxyInstance(new SearchService(), Search.class, 7);

		System.out.println(search2.search("a"));
		sleep(2);
		System.out.println(search2.search("a"));
		sleep(2);
		System.out.println(search2.search("b"));
		sleep(2);
		System.out.println(search2.search("a"));
		sleep(2);
		System.out.println(search2.search("b"));
		sleep(2);
		System.out.println(search2.search("a"));
		sleep(2);
	}
	
	private static void sleep(int n){
		try {
			Thread.sleep(1000 * n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
