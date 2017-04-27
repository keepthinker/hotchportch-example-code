package com.keepthinker.example.general.sql.example;

import com.keepthinker.example.general.sql.CompareCondition;
import com.keepthinker.example.general.sql.CompareSign;
import com.keepthinker.example.general.sql.SmartSQLGenerator;
import org.apache.log4j.Logger;

import java.util.Date;

public class SmartSQLMain {
	private static Logger logger = Logger.getLogger(SmartSQLMain.class);
	public static void main(String[] args){
		logger.info("start");
		Person person = new Person();
		person.setId(1);
		person.setMarried(false);
		person.setName(" ");
		person.setPhoneNumber(13692131586L);
		person.setWealth(1.8e9);
		person.setBirthDay(new Date());
		SmartSQLGenerator.getSQLEqualCondition(person);
		
		CompareCondition con = new CompareCondition();
		con.setColumnName("birthDay");
		con.setCompareSign(CompareSign.LESS_THAN_OR_EQUAL_TO);
		
		CompareCondition con1 = new CompareCondition();
		con1.setColumnName("wealth");
		con1.setCompareSign(CompareSign.GREATER_THAN);
		SmartSQLGenerator.getSQLCondition(person, java.sql.Timestamp.class, con, con1);
	}
}
