package com.keepthinker.example.nosql;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;

import java.util.List;

public class SpringRedisMain {
	private static AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
	public static void main(String[] args){
		StringRedisTemplate stringRedisTemplate = context.getBean(StringRedisTemplate.class);
		ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
		valueOps.set("spring", "spring");
		System.out.println(valueOps.get("spring"));
		stringRedisTemplate.delete("spring");
		
		String result = stringRedisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection stringRedisConnection = ((StringRedisConnection)connection);
				stringRedisConnection.set("spring", "spring");
				String str = stringRedisConnection.get("spring");
				stringRedisConnection.del("spring");
				return str;
			}
		});
		System.out.println(result);

		System.out.println(stringRedisTemplate.execute(new SessionCallback<String>() {
			@SuppressWarnings({ "rawtypes", "unchecked" }) 
			public String execute(RedisOperations operations) throws DataAccessException {
				operations.multi();
				operations.opsForValue().set("spring", "spring");
				operations.exec();
				String str = (String) operations.opsForValue().get("spring");
				operations.multi();
				operations.delete("spring");
				List<Object> objs =operations.exec();
				System.out.println("Number of items added to set: " + objs.get(0));
				return str;
			}
		}));
	}

}
