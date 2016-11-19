package com.keepthinker.example.cache.redis;

import java.util.Date;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RetrieveService {
	
	@Cacheable(value = "messageCache")
	public String retrieve(final String key){
		return key + " : " + new Date().toString();
	}
}
