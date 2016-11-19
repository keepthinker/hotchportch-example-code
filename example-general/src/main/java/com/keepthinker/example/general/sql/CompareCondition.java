package com.keepthinker.example.general.sql;

/**
 * Assist in sql "<" "<=" ">" or ">=" condition generation <br/>
 * @author keshengkai
 *
 */
public class CompareCondition {
	private String columnName;
	private CompareSign compareSign;
	
	public CompareCondition(){
	}

	public CompareCondition(String columnName, CompareSign compareSign) {
		super();
		this.columnName = columnName;
		this.compareSign = compareSign;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	/**
	 * The columnName parameter is column in DB in consistence with @Entity @Column.
	 * @param columnName
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public CompareSign getCompareSign() {
		return compareSign;
	}
	
	public void setCompareSign(CompareSign compareSign) {
		this.compareSign = compareSign;
	}
}
