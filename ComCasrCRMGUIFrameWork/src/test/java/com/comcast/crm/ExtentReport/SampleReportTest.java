package com.comcast.crm.ExtentReport;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {
	ExtentReports report;
	@BeforeSuite
	public void configBS() {

		// Spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// Add Enviroment information and create Test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window 11");
		report.setSystemInfo("Browser", "Chrome");
	}
	
	@AfterSuite
	public void configAS() {
		
		report.flush();
	}

	@Test
	public void createContactTest() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8888/");
		
		TakesScreenshot tks = (TakesScreenshot)driver;
		String Screenshot = tks.getScreenshotAs(OutputType.BASE64); 
		
		ExtentTest test = report.createTest("createContactTest");//Method Name
		test.log(Status.INFO,"Login to app");
		test.log(Status.INFO,"Navigate To Contact Page");
		test.log(Status.INFO,"Create Contact");

		if("HDFC".equals("HFDC")) {
			test.log(Status.PASS, "Pass");
		}else {
			test.addScreenCaptureFromBase64String(Screenshot,"ErrorFile");
		}	
		
		driver.close();
	}
}
