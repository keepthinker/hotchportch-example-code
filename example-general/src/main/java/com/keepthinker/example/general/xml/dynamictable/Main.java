package com.keepthinker.example.general.xml.dynamictable;

import com.keepthinker.example.general.xml.dynamictable.support.ObjectTableManager;
import com.keepthinker.example.general.xml.dynamictable.support.XMLFileParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		FileParser parser = new XMLFileParser();
		ObjectTable objectTable = new ObjectTableManager(parser, "config/mapper/object-table.xml");
		
		System.out.println(objectTable.getSql("database1", "table12"));
		System.out.println(objectTable.getSql("database2", "table21"));
		System.out.println(objectTable.getJdbcUrl("database1"));
	}
	
	public static FileParser mockData(){
		FileParser parser = new FileParser() {
			@Override
			public Collection<Database> parse(String relativePath) {
				Database database = new Database();
				database.setName("database");
				Table table = new Table();
				table.setName("table");
				Property property1 = new Property();
				property1.setColumn("name");
				Property property2 = new Property();
				property2.setColumn("age");
				Property property3 = new Property();
				property3.setColumn("salary");
				table.addProperty(property1);
				table.addProperty(property2);
				table.addProperty(property3);
				database.getTables().add(table);
				List<Database> dbs = new ArrayList<Database>();
				dbs.add(database);
				return dbs;
			}
		};
		return parser;
	}

}
