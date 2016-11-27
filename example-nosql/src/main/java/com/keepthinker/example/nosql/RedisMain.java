package com.keepthinker.example.nosql;

import redis.clients.jedis.Jedis;

public class RedisMain {
	public static void main(String[] args){
	      Jedis jedis = new Jedis("localhost");
	      System.out.println("Connection to server sucessfully");
	      //check whether server is running or not
	      System.out.println("Server is running: "+jedis.ping());
	      System.out.println(jedis.get("db"));
	      System.out.println(jedis.lpush("list", "12"));
	      System.out.println(jedis.lpop("list"));
	      jedis.close();
	}
}
