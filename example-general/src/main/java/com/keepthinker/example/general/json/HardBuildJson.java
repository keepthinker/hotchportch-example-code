package com.keepthinker.example.general.json;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class HardBuildJson {
	public static void main(String[] args){
		 ObjectMapper mapper = new ObjectMapper();

		 ObjectNode rootNode = mapper.createObjectNode();
		 ArrayNode marksNode = mapper.createArrayNode();
         marksNode.add(100);
         marksNode.add(90);
         marksNode.add(85);
         rootNode.put("name", "Mahesh Kumar");
         rootNode.put("age", 21);
         rootNode.put("verified", false);
         rootNode.replace("marks",marksNode);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("mapKey", "mapVal");
         rootNode.put("mapKey", map.get("mapKey").toString());
         System.out.println(rootNode.toString());
         
	}
}
