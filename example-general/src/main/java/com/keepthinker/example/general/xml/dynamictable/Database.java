package com.keepthinker.example.general.xml.dynamictable;

import org.apache.log4j.Logger;

import java.util.List;

public class Database{
	private static final Logger LOGGER = Logger.getLogger(Database.class);
	private List<Table> tables;
	private String name;
	private String displayName;
	private String url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Table> getTables() {
		return tables;
	}
	public void setTables(List<Table> tables) {
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("set new tables in database named " + name);
		}
		this.tables = tables;
	}

}
