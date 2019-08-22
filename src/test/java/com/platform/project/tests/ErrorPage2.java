package com.platform.project.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ErrorPage2 {
	WebDriver driver;
	String ConfigPathLocation="./config.properties";
	
	@BeforeMethod
	public void openHomePage() throws IOException {
		
		
		
		Properties pro = new Properties();
		FileInputStream in = new FileInputStream(ConfigPathLocation);
		pro.load(in);
		// getting values from property file
		String getBrowserValue = pro.getProperty("BrowserType");
		String getURL=pro.getProperty("sURL");
		if(getBrowserValue.equalsIgnoreCase("Chrome")) {
			
		
		//System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");-only in windows machine we need to write this piece of code
		driver = new ChromeDriver();
	}else {
		
		Assert.fail();
	}
		driver.get(getURL);
		
	}
	
	//emailaddress and password are blank
	
	@Test
	public void verifyLogIn() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\'bodyContent\']/div/div[1]/a[1]/u")).click();
		driver.findElement(By.xpath("//*[@id=\'tdb5\']/span[2]")).click();
		Thread.sleep(2000);
		String actual_error = driver.findElement(By.xpath("//*[@id=\'bodyContent\']/table/tbody/tr/td")).getAttribute("innerHTML");
		String expected_error =" Error: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actual_error.contains("Error: No match for E-Mail Address and/or Password"));
		System.out.println("Test Completed");
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.close();
	}
}
