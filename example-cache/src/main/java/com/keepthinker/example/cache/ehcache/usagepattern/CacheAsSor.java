package com.keepthinker.example.cache.ehcache.usagepattern;

import java.util.Collection;

import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.CacheEntryFactory;
import net.sf.ehcache.constructs.blocking.SelfPopulatingCache;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.writebehind.operations.SingleOperationType;

public class CacheAsSor {
	private final Ehcache cache; 
	public CacheAsSor(Ehcache cache) 
	{ 
		cache.registerCacheWriter(new MyCacheWriter()); 
		this.cache = new SelfPopulatingCache(cache, null); 
	} 
	/* read some data - notice the cache is treated as an SOR. 
	 * the application code simply assumes the key will always be available 
	 */ 
	public Object readSomeData(String key) 
	{ 
		return cache.get(key); 
	} 
	/* write some data - notice the cache is treated as an SOR, it is 
	 * the cache's responsibility to write the data to the SOR. 
	 */ 
	public void writeSomeData(String key, Object value) 
	{ 
		cache.put(new Element(key, value)); 
	} 
	/** 
	 * Implement the CacheEntryFactory that allows the cache to provide 
	 * the read-through strategy 
	 */ 
	private class MyCacheEntryFactory implements CacheEntryFactory 
	{ 
		public Object createEntry(Object key) throws Exception 
		{ 
			return readDataFromDataStore(key); 
		} 

		private Object readDataFromDataStore(Object key){
			return key;
		}
	} 
	/** 
	 * Implement the CacheWriter interface which allows the cache to provide 
	 * the write-through or write-behind strategy. 
	 */ 
	private class MyCacheWriter implements CacheWriter {

		public CacheWriter clone(Ehcache cache) throws CloneNotSupportedException {
			return null;//		} 

			//		throw new CloneNotSupportedException(); 
		} 
		@Override
		public void init() { }
		@Override
		public void dispose() throws CacheException { } 
		@Override
		public void write(Element element) throws CacheException
		{ 
			//		writeDataToDataStore(element.getKey(), element.getValue()); 
		} 
		@Override
		public void writeAll(Collection<Element> elements) throws CacheException 
		{ 
			for (Element element : elements) { 
				write(element); 
			} 
		} 
		@Override
		public void delete(CacheEntry entry) throws CacheException 
		{ 
			//		deleteDataFromDataStore(element.getKey()); 
		} 
		@Override
		public void deleteAll(Collection<CacheEntry> entries) throws CacheException 
		{ 
			//		for (Element element : elements) { 
			//			delete(element); 
			//		} 
		} 

		private void writeDataToDataStore(String key, Object value){
		}

		@Override
		public void throwAway(Element element, SingleOperationType operationType, RuntimeException e) {
			// TODO Auto-generated method stub

		}
	}

}
