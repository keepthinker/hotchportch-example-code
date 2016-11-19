package com.keepthinker.example.general.xml.dynamictable.support;

import java.util.ArrayList;
import java.util.List;

import com.keepthinker.example.general.xml.dynamictable.Database;
import com.keepthinker.example.general.xml.dynamictable.FileParser;
import com.keepthinker.example.general.xml.dynamictable.ObjectTable;
import com.keepthinker.example.general.xml.dynamictable.Property;
import com.keepthinker.example.general.xml.dynamictable.Table;

public class ObjectTableManager implements ObjectTable{
	
	private List<Database> databases = new ArrayList<Database>(5);
	
	protected FileParser parser; 
	
	private String configFilePath;
	
	public ObjectTableManager(FileParser parser, String configFilePath){
		this.configFilePath = configFilePath;
		this.parser = parser;
		init();
	}
	
	private void init() {
		databases.addAll(parser.parse(configFilePath));
	}
	
	public Database getDatabaseByName(String name){
		for(Database database : databases){
			if(database.getName().equals(name)){
				return database;
			}
		}
		return null;
	}
	
	public Table getTableByName(String databaseName, String tableName){
		Database database = getDatabaseByName(databaseName);
		if(database == null){
			return null;
		}
		List<Table> tables = database.getTables();
		if(tables == null){
			return null;
		}
		for(Table table : tables){
			if(table.getName().equals(tableName)){
				return table;
			}
		}
		return null;
	}

	@Override
	public String getSql(String databaseName, String tableName) {
		Table table = getTableByName(databaseName, tableName);
		if(table == null){
			return null;
		}
		StringBuilder builder = new StringBuilder();
		builder.append("select ");
		for(Property property : table.getProperties()){
			builder.append(property.getColumn()).append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(" from ").append(tableName);
		return builder.toString();
	}

	@Override
	public String getJdbcUrl(String databaseName) {
		return getDatabaseByName(databaseName).getUrl();
	}

}
