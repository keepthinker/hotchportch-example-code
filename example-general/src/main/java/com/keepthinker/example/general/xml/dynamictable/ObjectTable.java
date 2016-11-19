package com.keepthinker.example.general.xml.dynamictable;


public interface ObjectTable {
	String getSql(String databaseName, String tableName);
	String getJdbcUrl(String databaseName);
}
