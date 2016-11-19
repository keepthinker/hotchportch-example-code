package com.keepthinker.example.cache.ehcache.usagepattern;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class CacheAside {
	private final Ehcache cache; 
	public CacheAside(Ehcache cache) 
	{ 
		this.cache = cache; 
	} 
	/* read some data, check cache first, otherwise read from sor */ 
	public Object readSomeData(String key) 
	{ 
		Element element; 
		if ((element = cache.get(key)) != null) { 
			return element.getObjectValue(); 
		} 
		// note here you should decide whether your cache 
		// will cache 'nulls' or not 
		Object value;
		if ((value = readDataFromDataStore(key)) != null) { 
			cache.put(new Element(key, value)); 
		} 
		return value; 
	} 
	/* write some data, write to sor, then update cache */ 
	public void writeSomeData(String key, Object value) 
	{ 
		writeDataToDataStore(key, value); 
		cache.put(new Element(key, value)); 
	}
	
	private Object readDataFromDataStore(String key){
		return key;
	}

	private void writeDataToDataStore(String key, Object value){
	}
	
}