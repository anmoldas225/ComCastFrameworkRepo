package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcelString(String SheetName, int rowNum, int cellnum)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./src/main/resources/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row rw = sh.getRow(rowNum);
		Cell cell = rw.getCell(cellnum);
		String value = cell.getStringCellValue();
		wb.close();
		return value;
	}

	public int getDataFromExcelNumeric(String sheetName, int rowNum, int cellnum)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./src/main/resources/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh    = wb.getSheet(sheetName);
		Row rw      = sh.getRow(rowNum);
		Cell cell   = rw.getCell(cellnum);
		int value   = (int) cell.getNumericCellValue();// this line value in double but we convert it into int
		wb.close();
		return value;
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rownum = sh.getLastRowNum();
		wb.close();
		return rownum;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int celnum, String data) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/TestScriptData.xlsx");
		Workbook wb  = WorkbookFactory.create(fis);
		Sheet sh     = wb.getSheet(sheetName);
		Row rw       = sh.getRow(rowNum);
		rw.createCell(celnum).setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream("./src/main/resources/TestScriptData.xlsx");
		wb.write(fos);
		wb.close();
	}

}
