package com.keepthinker.example.general.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Generate sql from entity with or without @Column annotated.<br/>
 * Ignore blank, null value such as "", "   ", null.<br/>
 * Have risk of sql injection.
 * @author keshengkai
 *
 */
public final class SmartSQLGenerator {
	
	private static final ClassLoader currentThreadContextClassLoader = Thread.currentThread().getContextClassLoader();

	private SmartSQLGenerator(){
	}

	private static final Logger LOGGER = Logger.getLogger(SmartSQLGenerator.class);

	/**
	 * By default, it converts java.util.Date to java.sql.Timestamp
	 */
	public static String getSQLCondition(Object obj, CompareCondition... conditions){
		return getSQLCondition(obj, Timestamp.class, conditions);
	}
	/**
	 * if parameter obj class does not comply to JavaBean standard, it'll return null;
	 * @param obj JavaBean Standard
	 * @param timeType  class in java.sql.*. for example, if timeType is Timestamp.class, it will generate sth. like '2015-12-08 13:06:58.004'.
	 * @param compareConditions
	 * @return
	 */
	public static String getSQLCondition(Object obj, Class<? extends Date> timeType, CompareCondition... compareConditions){
		Assert.notNull(obj, "obj must not be null");
		Assert.notNull(timeType, "timeType must not be null");

		Map<String, Object> compareColNameSet = null;
		if(compareConditions != null){
			compareColNameSet = new HashMap<String, Object>(8);
			for(int i = 0, len = compareConditions.length; i < len ; i++){
				compareColNameSet.put(compareConditions[i].getColumnName(), compareConditions[i]);
			}
		}

		StringBuilder conditionBuilder = new StringBuilder();

		Set<String> set = compareColNameSet.keySet();
		try {
			Class<?> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			fields = removeUselessField(fields);

			for(int i = 0, leni = fields.length; i < leni; i++){
				Object value = getFieldValue(obj, fields[i]);
				if(value == null || value.toString().trim().length() == 0){
					continue;
				}
				if(value instanceof Date){
					value = timeType.getConstructor(long.class).newInstance(((Date)value).getTime());
				}
				Annotation[] annos = fields[i].getDeclaredAnnotations();
				String columnName = "";
				for(int j = 0, lenj = annos.length; j < lenj; j++){
					if(annos[j] instanceof Column){
						columnName = ((Column)annos[j]).name();
						break;
					}
					if(LOGGER.isDebugEnabled()){
						LOGGER.debug("Embeded Column is not used, so to check javax.persistence.Column");
					}
					Class<?> jpaColumn = null;
					try{
						jpaColumn = currentThreadContextClassLoader.loadClass("javax.persistence.Column");
					}catch(Exception e){
						if(LOGGER.isDebugEnabled()){
							LOGGER.debug("javax.persistence.Column is not in classpath");
						}
					}
					if(jpaColumn != null && annos[j].getClass() == jpaColumn){
						columnName = ((Column)annos[j]).name();
						break;
					}
					if(annos[j].getClass().getName().contains("Colunm")){
						if(LOGGER.isDebugEnabled()){
							LOGGER.warn("The type of Column Annotation is not supported ");
						}
					}
				}
				if(annos.length == 0){
					columnName = fields[i].getName();
				}

				if(compareColNameSet != null){
					if(set.contains(columnName)){
						CompareSign compareSign = ((CompareCondition) compareColNameSet.get(columnName)).getCompareSign();
						conditionBuilder.append(columnName).append(compareSign).append(value).append(" and ");
					}else{
						conditionBuilder.append(columnName).append(" = ").append(value).append(" and ");
					}
				}else{
					conditionBuilder.append(columnName).append(" = ").append(value).append(" and ");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		String result = conditionBuilder.substring(0, conditionBuilder.length()-4);
		LOGGER.debug(result);
		return result;
	}

	private static Field[] removeUselessField(Field[] fields){
		List<Field> fieldList = new ArrayList<Field>();
		for(int i = 0; i < fields.length; i++){
			if (java.lang.reflect.Modifier.isStatic(fields[i].getModifiers()) == false
					&& java.lang.reflect.Modifier.isFinal(fields[i].getModifiers()) == false
					&& java.lang.reflect.Modifier.isPublic(fields[i].getModifiers()) == false) {
				fieldList.add(fields[i]);
			}
		}
		fields = new Field[fieldList.size()];
		fields = fieldList.toArray(fields);
		return fields;
	}

	/**
	 * By default, it converts java.util.Date to Timestamp
	 * @param obj
	 * @return
	 */
	public static String getSQLEqualCondition(Object obj){
		return getSQLEqualCondition(obj, Timestamp.class);
	}

	/**
	 * @param obj
	 * @param timeType DB time type
	 * @return
	 */
	public static String getSQLEqualCondition(Object obj, Class<? extends Date> timeType){
		return getSQLCondition(obj, timeType);
	}

	private static Object getFieldValue(Object obj, Field field) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> clazz = obj.getClass();
		String name = field.getName();
		String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
		Object value;
		if(field.getType() == boolean.class || field.getType() == Boolean.class){
			value = clazz.getMethod("is" + capitalizedName).invoke(obj);
		}else{
			value = clazz.getMethod("get" + capitalizedName).invoke(obj);
		}
		return value;
	}
}
