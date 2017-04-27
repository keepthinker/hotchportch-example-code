package com.keepthinker.example.general.xml;

import com.keepthinker.example.general.util.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstPrint {
	
	public void print(String relativePath) {
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(Utils.getContextClasspath() + relativePath));
		Element elem = doc.getDocumentElement();

		List<Node> que =new LinkedList<Node>();
		que.add(elem);
		while(true){
			if(que.size() ==0){
				break;
			}
			Node node = que.remove(0);
			if(node.getNodeName().equals("#text")){
				que.remove(node);
				continue;
			}
			System.out.println(node.getNodeName());
			NodeList nl = node.getChildNodes();
			for(int i = 0, len = nl.getLength(); i < len; i++){
				Node cnode = nl.item(i);
				que.add(cnode);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
