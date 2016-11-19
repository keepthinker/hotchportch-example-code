package com.keepthinker.example.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.StatisticsGateway;

public class App {
    public static void main( String[] args ) {
    	
    	// Create a cache manager
    	CacheManager cacheManager = CacheManager.create(App.class.getResource("/ehcache-example.xml"));

//    	cacheManager.addCache("hello-world");
    	
        // create the cache called "hello-world"
        Cache cache = cacheManager.getCache("cache1"); //configured in classpath:ehcache.xml

        // create a key to map the data to
        String key = "greeting";

        // Create a data element
        Element putGreeting = new Element(key, "Hello, World!");
        Element putName = new Element("name", "Jenny");
        
        // Put the element into the data store
        cache.put(putGreeting);
        cache.put(putName);
        
        //This updates the entry for "name" 
        cache.put(new Element("name", "Max"));
        
        // Retrieve the data element
        Element getGreeting = cache.get(key);
        Element getName = cache.get("name");

        // Print the value
        System.out.println(getGreeting.getObjectValue());
        System.out.println(getName.getObjectValue());
        
        //The following gets the number of elements currently in the cache
        int elementsInMemory = cache.getSize();
        System.out.println(elementsInMemory);
        //The following gets the number of elements currently in the MemoryStore.
        StatisticsGateway statistics = cache.getStatistics();
        System.out.println(statistics.getLocalDiskSizeInBytes());
        
        
        cacheManager.shutdown();
    }
}
