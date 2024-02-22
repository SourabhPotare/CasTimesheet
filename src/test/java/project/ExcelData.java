package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import objectModel.Timesheet;

public class ExcelData {
	
	public static String Input() throws IOException {
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\Excel\\ExcelInput.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet sheet = wb.getSheet("Input");
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(0);
		String input = cell.getStringCellValue();
		return input;
	}
	
	public static void output(List<WebElement> userDetails) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet("UserDetails");
		XSSFRow headerRow = sh.createRow(0);
		headerRow.createCell(0).setCellValue("User details: ");
		
		for(int i=0;i<2;i++) {
			XSSFRow row = sh.createRow(i+1);
			row.createCell(0).setCellValue(userDetails.get(i).getText());
		}
		
		System.out.println("Print successfully.......!!!!");
		FileOutputStream fileout = new FileOutputStream(System.getProperty("user.dir")+"\\Excel\\ExcelOutput.xlsx");	
		wb.write(fileout);
		wb.close();
	}
	
	public static void output1(String title) throws IOException{
		File fs = new File(System.getProperty("user.dir")+"\\Excel\\ExcelOutput.xlsx");
		FileInputStream inputStream = new FileInputStream(fs);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sh = wb.createSheet(title);
		XSSFRow headerRow = sh.createRow(0);
		headerRow.createCell(0).setCellValue("Sheet Date");
		headerRow.createCell(1).setCellValue("Sheet Status");
		for(int i=0;i<Timesheet.getDate().size();i++) {
			XSSFRow row = sh.createRow(i+1);
			row.createCell(0).setCellValue(Timesheet.getDate().get(i).getText());
			row.createCell(1).setCellValue(Timesheet.getStatus().get(i).getText());
		}
		
		System.out.println("Print successfully......!!!!");
		FileOutputStream fileout = new FileOutputStream(System.getProperty("user.dir")+"\\Excel\\ExcelOutput.xlsx");	
		wb.write(fileout);
		wb.close();
		
	}
	public static void output2(String title) throws IOException{
		File fs = new File(System.getProperty("user.dir")+"\\Excel\\ExcelOutput.xlsx");
		FileInputStream inputStream = new FileInputStream(fs);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sh = wb.createSheet(title);
		XSSFRow headerRow = sh.createRow(0);
		headerRow.createCell(0).setCellValue("Sheet Date");
		headerRow.createCell(1).setCellValue("Sheet Status");
		XSSFRow row = sh.createRow(1);
		row.createCell(0).setCellValue(0);
		row.createCell(1).setCellValue(0);
		System.out.println("Print successfully......!!!!");
		FileOutputStream fileout = new FileOutputStream(System.getProperty("user.dir")+"\\Excel\\ExcelOutput.xlsx");	
		wb.write(fileout);
		wb.close();
		
	}
	
	
	
	
	
	

}
