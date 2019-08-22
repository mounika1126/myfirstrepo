package com.platform.project.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePage {
	WebDriver driver;
	String ConfigPathLocation="./config.properties";
	String Emailaddress;
	String pwd;
	
	
	@BeforeMethod
	public void openHomePage() throws IOException {
		//System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");-only in windows machine we need to write this piece of code
	     Properties p = new Properties();
	     FileInputStream in = new FileInputStream(ConfigPathLocation);
	     p.load(in);
	     //getting values from property file
	     String getBrowserValue = p.getProperty("BrowserType");
	     String getURL = p.getProperty("sURL");
	     if(getBrowserValue.equalsIgnoreCase("Chrome")) {
	    	 driver = new ChromeDriver();
	     }else {
	    	 Assert.fail();
	     }
	     driver.get(getURL);
	}
	
	@Test
	public void verifyLogIn() throws IOException {
		driver.findElement(By.xpath("//*[@id=\'bodyContent\']/div/div[1]/a[1]/u")).click();
		
		File file=new File("/Users/mona/Documents/Book1.xlsx");
		FileInputStream fi=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheetAt(0);
		
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell1 = row.getCell(0);
		XSSFCell cell2 = row.getCell(1);
		
		String sEmail = cell1.toString();
		String pwd = cell2.toString();
		
		
		driver.findElement(By.name("email_address")).sendKeys(sEmail);
		
		driver.findElement(By.name("password")).sendKeys(pwd);
		
		
		
		driver.findElement(By.xpath("//*[@id=\'tdb5\']/span[2]")).click();
		driver.findElement(By.xpath("//*[@id=\'tdb4\']/span")).click();
		
		System.out.println("Test is passed");
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.close();
	}

}
