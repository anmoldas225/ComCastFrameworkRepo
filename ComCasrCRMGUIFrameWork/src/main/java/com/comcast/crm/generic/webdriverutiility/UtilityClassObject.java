package com.comcast.crm.generic.webdriverutiility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
	
	
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static ExtentTest getTest() {
		return test.get();
	}
	public static void setTest(ExtentTest extentTest) {
		test.set(extentTest);
	}
	public static WebDriver getDriver() {
		return driver.get();
	}
	public static void setDriver(WebDriver webDriver) {
		 driver.set(webDriver);
	}
	
	
	

}
