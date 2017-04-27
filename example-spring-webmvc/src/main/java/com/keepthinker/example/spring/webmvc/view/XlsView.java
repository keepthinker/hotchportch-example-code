package com.keepthinker.example.spring.webmvc.view;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class XlsView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook wb, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment;filename=" + "a.xls");
		
		HSSFSheet sheet;
		HSSFCell cell;
		// Go to the first sheet
		// getSheetAt: only if wb is created from an existing document
		// sheet = wb.getSheetAt(0);
		sheet = wb.createSheet("S pring");
		// write a text at A1
		cell = getCell(sheet, 0, 0);
		setText(cell, "Spring-Excel test");
		@SuppressWarnings("unchecked")
		List<String> words = (List<String>) model.get("wordList");
		for (int i=0; i < words.size(); i++) {
			cell = getCell(sheet, 2+i, 0);
			setText(cell, words.get(i));
		}

	}

}
