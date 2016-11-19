package com.keepthinker.example.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public class AddAndRemoveCache {

	public static void main(String[] args) {
		CacheManager singletonManager = CacheManager.create(); 
		singletonManager.addCache("testCache1"); 
		Cache test1 = singletonManager.getCache("testCache1");

		Cache memoryOnlyCache = new Cache("testCache2", 5000, false, false, 5, 2); 
		singletonManager.addCache(memoryOnlyCache); 
		Cache test2 = singletonManager.getCache("testCache2");
		
		//Create a Cache specifying its configuration. 
		Cache testCache3 = new Cache( 
		  new CacheConfiguration("testCache3", 1000) 
		    .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU) 
		    .eternal(false) 
		    .timeToLiveSeconds(60) 
		    .timeToIdleSeconds(30) 
		    .diskExpiryThreadIntervalSeconds(0) 
		    .persistence(new PersistenceConfiguration().strategy(Strategy.LOCALTEMPSWAP))); 
		singletonManager.addCache(testCache3);

		singletonManager.clearAll();
		singletonManager.removeCache("testCache1");
		singletonManager.removeAllCaches();
		singletonManager.shutdown();
	}

}
