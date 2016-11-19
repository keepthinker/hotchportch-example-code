package com.keepthinker.example.general.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keepthinker.example.general.json.Person.Sex;

public class JacksonMain {
	private static ObjectMapper mapper = new ObjectMapper();
	public static void main(String[] args){
		hotchpotch2();
	}

	public static void writeToJsonStr(){
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>(); 
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "John");
		listMap.add(map);
		try {
			long s = System.currentTimeMillis();
			System.out.println(mapper.writeValueAsString(listMap));
			long e = System.currentTimeMillis();
			System.out.println(e - s);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public static void hotchpotch(){
		mapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
		try {
			String jsonStr = "{\"birthDay\": \"2015/01/02\", \"birthTime\": 12313, \"Name\": \"name\"}";
			Person person = mapper.readValue(jsonStr, Person.class);
			System.out.println(mapper.readTree(jsonStr).get("birthTime").asInt());
			System.out.println(person);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void hotchpotch2(){
		mapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
		try {

			Person person = new Person();
			person.setSex(Sex.FEMALE);
			System.out.println(mapper.writeValueAsString(person));

			String jsonStr = "{\"birthDay\": \"2015/01/02\", \"birthTime\": 12313, \"Name\": \"name\", \"sex\": \"FEMALE\"}";
			person = mapper.readValue(jsonStr, Person.class);
			System.out.println(person);
			System.out.println(mapper.writeValueAsString(person));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

