package com.platform.project.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadexcelFile {
	
	public static void main(String[] args) throws Exception {
		File file = new File("/Users/mona/Documents/Book1.xlsx");
		FileInputStream input = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(input);
		XSSFSheet sheet = wb.getSheetAt(0);
		String text = sheet.getRow(0).getCell(0).getStringCellValue();
		sheet.getRow(1).getCell(0).getStringCellValue();
		
		
	}

}
