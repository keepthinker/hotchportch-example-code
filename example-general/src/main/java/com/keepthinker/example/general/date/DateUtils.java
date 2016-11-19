package com.keepthinker.example.general.date;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Example:<br/><br/>
 * java.util.Date pre1WeekDate = DateUtils.getDate(new Date(), -7, Calendar.DAY_OF_YEAR);<br/><br/>
 * java.util.Date pre1MonthDate = DateUtils.getDate(new Date(), -1, Calendar.MONTH);<br/><br/>
 * Time time = DateUtils.getTime(cal.getTime(), -1, Calendar.MONTH);
 * @author keshengkai
 */
public class DateUtils {

	public static java.util.Date getUtilDate(Date baseDay, int delta, int dateType){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(baseDay);
		calendar.add(dateType, delta);
		return calendar.getTime();
	}

	public static Timestamp getTimestamp(Date baseDay, int delta, int dateType){
		return new Timestamp(DateUtils.getUtilDate(baseDay, delta, dateType).getTime());
	}

	public static java.sql.Date getSqlDate(Date baseDay, int delta, int dateType){
		return new java.sql.Date(DateUtils.getUtilDate(baseDay, delta, dateType).getTime());
	}

	public static Time getTime(Date baseDay, int delta, int dateType){
		return new Time(DateUtils.getUtilDate(baseDay, delta, dateType).getTime());
	}
}
