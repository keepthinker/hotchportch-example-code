package com.keepthinker.example.general.xml.dynamictable;

import java.util.List;

import org.apache.log4j.Logger;

import com.keepthinker.example.general.xml.dynamictable.Property;
public class Table {
	private static final Logger LOGGER = Logger.getLogger(Table.class);
	private String name;
	private String displayName;
	private List<Property> properties;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("set new properties in table named " + name);
		}
		this.properties = properties;
	}
	public void addProperty(Property property){
		properties.add(property);
	}
}
