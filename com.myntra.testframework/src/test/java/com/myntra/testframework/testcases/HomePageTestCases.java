package com.myntra.testframework.testcases;


import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.myntra.testframework.factory.BrowserFactory;
import com.myntra.testframework.factory.DataProviderFactory;
import com.myntra.testframework.pageobject.HomePageObject;
import com.myntra.testframework.utility.Helper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTestCases {
	
	HomePageObject page1;
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	
	
	@BeforeTest
	public void beforeTestCase() throws IOException
	{
		report=new ExtentReports("C:\\Users\\Yogesh\\Selenium_MyntraFramework\\com.myntra.testframework\\Report\\report.html",true);
		logger=report.startTest("HomePageTestCases");
		page1=new HomePageObject();
	    driver = BrowserFactory.getDriver("firefox");

		PageFactory.initElements(driver, page1);
	}
	@BeforeMethod
	public void beforeTestmethod() throws IOException
	{
		
	 
	  driver.get(DataProviderFactory.getDataProviderFromConfig().getUrl());
	  
	  logger.log(LogStatus.INFO, "Navgivate to Myntra URL");
	  
	  
	  
	  
	}
	
	@Test
	public void tabsPresentOrNot() throws Exception
	{
	 
	 
		
	  assertEquals(page1.getManlink().getText(),"Men");
      
      logger.log(LogStatus.INFO, "ManLink is Present");
      
      assertEquals(page1.getWomenLink().getText(),"Women");
      
      logger.log(LogStatus.INFO, "Women is Present");
      
      assertEquals(page1.getKidsLink().getText(),"Kids");
      
      logger.log(LogStatus.INFO, "Kids is Present");
      
      assertEquals(page1.getHome_LivingLink().getText(),"Home & Living");
      
      logger.log(LogStatus.INFO, "Home and Living link is Present");
      logger.log(LogStatus.PASS, Helper.getScreenshot(driver, "screenshotafterpass"));
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception
	{
		//BrowserFactory.closeDriver(driver);
		
		if(ITestResult.FAILURE==result.getStatus())
		{
			String screenshot = Helper.getScreenshot(driver, result.getName());
			
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshot));		
			
		}
		
		report.endTest(logger);
		report.flush();
		
	}
	

}
