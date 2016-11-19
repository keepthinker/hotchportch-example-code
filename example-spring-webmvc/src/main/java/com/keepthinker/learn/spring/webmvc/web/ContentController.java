package com.keepthinker.example.spring.webmvc.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.keepthinker.example.spring.webmvc.model.Person;

@Controller
@RequestMapping(value = "/content")
public class ContentController {
	
	@RequestMapping(value = "/xls", method = RequestMethod.GET)
	public ModelAndView produceXlsFile(){
		System.out.println("-----");
		String[] strArr = {"1", "1", "1", "1", "1", "2"};
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("wordList", Arrays.asList(strArr));
		return new ModelAndView("xls", model);
	}
	
	@RequestMapping(value = "/xlsx", method = RequestMethod.GET)
	public void produceXlsxFile(HttpServletResponse res) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		System.out.println("-----");
		String[] titles = {"name", "birthplace"};

		model.put("titles", titles);
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("John", "British"));
		persons.add(new Person("Max", "USA"));
		model.put("persons", persons);
		model.put("downloadFileName", "hi.xlsx");
		model.put("sheetname", "person sheet");
		produceXlsx(res, model);
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String upload(@RequestParam("name") String name,	@RequestParam("file") MultipartFile file, Model model) throws UnsupportedEncodingException, IOException{
		System.out.println("-----" + file.getSize());
		model.addAttribute("fileName", file.getOriginalFilename());
		String fileContent = new String(file.getBytes(),"utf-8");
		model.addAttribute("fileContent", fileContent);
		return "upload-success";
	}

	@SuppressWarnings("unchecked")
	private void produceXlsx(HttpServletResponse res, Map<String, Object> model) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
		OutputStream out = null;
		try {
			res.setCharacterEncoding("utf-8");
			res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
			String filename = (String) model.get("downloadFileName");
			res.setHeader("Content-Disposition", "attachment;filename=" + filename);
			out = res.getOutputStream();
			XSSFSheet spreadsheet = workbook.createSheet((String) model.get("sheetname"));
			int rowid = 0;
			XSSFRow row = null;
			row = spreadsheet.createRow(rowid ++);
			String[] titles = (String[]) model.get("titles");
			int cellid = 0;
			for(int i = 0, len = titles.length; i < len; i++){
				Cell cell= row.createCell(cellid ++);
				Font font = workbook.createFont();
				font.setBold(true);
				font.setColor(HSSFColor.BLUE.index);
				XSSFCellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setFont(font);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(titles[i]);
			}
			List<Person> persons = (List<Person>) model.get("persons");
			for (Person po : persons) {
				row = spreadsheet.createRow(rowid++);
				cellid= 0;
				Cell cell= row.createCell(cellid ++);
				cell.setCellValue(po.getName());
				cell= row.createCell(cellid ++);
				cell.setCellValue(po.getBirthplace());
			}
			workbook.write(out);
			workbook.close();
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != workbook)
				workbook.close();
			if (null != out)
				out.close();
		}
	}
	
	
}
