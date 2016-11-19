package com.keepthinker.example.general.poi.example;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteImei {
	public static void main(String[] args) throws Exception{
		createImeiXLSX((long)1e14 + 1900, 2001);
	}
	
	public static void createImeiXLSX(long begin, int size) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		XSSFSheet spreadsheet = workbook.createSheet( 
				" IMEI Info ");
		XSSFRow row;
		int rowid = 0;
		long count = begin;
		for (int i = 0; i < size; i++)
		{
			row = spreadsheet.createRow(rowid++);
			Cell cell = row.createCell(0);
			cell.setCellValue(String.valueOf(count + i));
		}
		//Write the workbook in file system
		FileOutputStream out = new FileOutputStream( 
				new File("imei-"+ begin + "-" + size +".xlsx"));
		workbook.write(out);
		out.close();
		System.out.println( 
				"Writesheet.xlsx written successfully" ); 
		workbook.close();
	}
}
