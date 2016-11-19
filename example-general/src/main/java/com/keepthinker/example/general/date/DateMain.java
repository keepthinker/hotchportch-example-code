package com.keepthinker.example.general.date;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateMain {
	public static void main(String[] args){
		Date pre1WeekDate = DateUtils.getUtilDate(new Date(), -60, Calendar.DAY_OF_MONTH);
		System.out.println(pre1WeekDate);

		Date pre1WeekDate1 = DateUtils.getUtilDate(new Date(), -60, Calendar.DAY_OF_YEAR);
		System.out.println(pre1WeekDate1);

		Date pre1MonthDate = DateUtils.getUtilDate(new Date(), -2, Calendar.MONTH);
		System.out.println(pre1MonthDate);

		Calendar cal = Calendar.getInstance();
		cal.set(2015, 2, 31);
		java.sql.Date date1 = DateUtils.getSqlDate(cal.getTime(), -1, Calendar.MONTH);
		System.out.println(date1);
		
		Time time = DateUtils.getTime(cal.getTime(), -1, Calendar.MONTH);
		System.out.println(time);
		
		Timestamp timestamp = DateUtils.getTimestamp(cal.getTime(), -1, Calendar.MONTH);
		System.out.println(timestamp);
	}
}
