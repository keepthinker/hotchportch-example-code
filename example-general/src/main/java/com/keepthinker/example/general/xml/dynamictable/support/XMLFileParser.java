package com.keepthinker.example.general.xml.dynamictable.support;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.keepthinker.example.general.util.Utils;
import com.keepthinker.example.general.xml.dynamictable.Database;
import com.keepthinker.example.general.xml.dynamictable.FileParser;
import com.keepthinker.example.general.xml.dynamictable.Property;
import com.keepthinker.example.general.xml.dynamictable.Table;

public class XMLFileParser implements FileParser {
	private static final Logger LOGGER = Logger.getLogger(XMLFileParser.class);
	
	private static final String ROOT_NODE_NAME = "object-table";
	
	private static final String DATABASE_NODE_NAME = "database";
	private static final String DATABASE_NAME_ATTR = "name";
	private static final String DATABASE_URL_ATTR = "url";
	private static final String DATABASE_DISPLAY_NAME_ATTR = "displayName";
	
	private static final String TABLE_NODE_NAME = "table";
	private static final String TABLE_NAME_ATTR = "name";
	private static final String TABLE_DISPLAY_NAME_ATTR = "displayName";
	
	private static final String PROPERTY_NODE_NAME = "property";
	private static final String PROPERTY_COLUMN_ATTR = "column";
	private static final String PROPERTY_NAME_ATTR = "name";
	private static final String PROPERTY_DISPLAY_NAME_ATTR = "displayName";
	private static final String PROPERTY_TYPE_ATTR = "type";
	
	@Override
	public List<Database> parse(String relativePath) {
		String absolutePath = Utils.getContextClasspath() + relativePath;
		List<Database> databases;
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File(absolutePath));
			Element rootElem = doc.getDocumentElement();
			if(!rootElem.getNodeName().equals(ROOT_NODE_NAME)){
				throw new Exception();
			}
			databases = parseDatabase(rootElem);
		}catch(Exception e){
			LOGGER.error("Failed to parse " + absolutePath);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return databases;
	}
	
	private List<Database> parseDatabase(Element rootElem){
		List<Database> databases = new ArrayList<Database>(8);
		Database database;
		NodeList databaseNodeList = rootElem.getChildNodes();
		for(int i = 0, len = databaseNodeList.getLength(); i < len; i++){
			Node databaseNode = databaseNodeList.item(i);
			if(!databaseNode.getNodeName().equals(DATABASE_NODE_NAME)){
				continue;
			}
			database = new Database();
			NamedNodeMap map = databaseNode.getAttributes();
			database.setName(map.getNamedItem(DATABASE_NAME_ATTR).getNodeValue());
			database.setUrl(map.getNamedItem(DATABASE_URL_ATTR).getNodeValue());
			database.setDisplayName(map.getNamedItem(DATABASE_DISPLAY_NAME_ATTR).getNodeValue());
			database.setTables(parseTable(databaseNode));
			databases.add(database);
		}
		return databases;
	}
	
	private List<Table> parseTable(Node databaseNode){
		List<Table> tables = new ArrayList<Table>(8);
		NodeList tableNodeList = databaseNode.getChildNodes();
		Table table;
		for(int i = 0, len = tableNodeList.getLength(); i < len; i++){
			Node tableNode = tableNodeList.item(i);
			if(!tableNode.getNodeName().equals(TABLE_NODE_NAME)){
				continue;
			}
			table = new Table();
			NamedNodeMap map = tableNode.getAttributes();
			table.setName(map.getNamedItem(TABLE_NAME_ATTR).getNodeValue());
			table.setDisplayName(map.getNamedItem(TABLE_DISPLAY_NAME_ATTR).getNodeValue());
			table.setProperties(parseProperty(tableNode));
			tables.add(table);
		}
		return tables;
	}

	private List<Property> parseProperty(Node tableNode){
		List<Property> properties = new ArrayList<Property>(8);
		NodeList propertiesNodeList = tableNode.getChildNodes();
		Property property;
		for(int i = 0, len = propertiesNodeList.getLength(); i < len; i++){
			Node propertyNode = propertiesNodeList.item(i);
			if(!propertyNode.getNodeName().equals(PROPERTY_NODE_NAME)){
				continue;
			}
			property = new Property();
			NamedNodeMap map = propertyNode.getAttributes();
			property.setColumn(map.getNamedItem(PROPERTY_COLUMN_ATTR).getNodeValue());
			property.setName(map.getNamedItem(PROPERTY_NAME_ATTR).getNodeValue());
			property.setDisplayName(map.getNamedItem(PROPERTY_DISPLAY_NAME_ATTR).getNodeValue());
			property.setType(map.getNamedItem(PROPERTY_TYPE_ATTR).getNodeValue());
			properties.add(property);
		}
		return properties;
	}

}
