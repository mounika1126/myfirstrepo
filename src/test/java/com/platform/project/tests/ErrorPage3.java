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

public class ErrorPage3 {
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
	//email address is blank
	@Test
	public void verifyLogIn() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\'bodyContent\']/div/div[1]/a[1]/u")).click();
		driver.findElement(By.name("password")).sendKeys("test123");
		driver.findElement(By.xpath("//*[@id=\'tdb5\']/span[2]")).click();
		Thread.sleep(2000);
		String actual_error=driver.findElement(By.xpath("//*[@id=\'bodyContent\']/table/tbody/tr/td")).getText();
		String expected_error=" Error: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actual_error,expected_error);
		System.out.println("The error message is verified");
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.close();
	}

}

