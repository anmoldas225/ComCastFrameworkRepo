package com.comcast.crm.generic.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.baseutility.BaseClass;
import com.comcast.crm.generic.webdriverutiility.UtilityClassObject;

public class ListenerImpClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public static ExtentReports report;
    public static ExtentTest test; 
	
    @Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report");
		spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// Add Enviroment information and create Test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window 11");
		report.setSystemInfo("Browser", "Chrome");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report BackUp");
		report.flush();
	}
	
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("===========" + result.getMethod().getMethodName() + "==========");
	    
		test = report.createTest(result.getMethod().getMethodName());//Method Name
		UtilityClassObject.setTest(test);
		test.log(Status.INFO,result.getMethod().getMethodName() + "<=====STARTS======>" );
	}
	

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("===========" + result.getMethod().getMethodName() + "=========");
		test.log(Status.INFO,result.getMethod().getMethodName() + "<=====COMPLETED======>" );

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		 String testName = result.getMethod().getMethodName();
		 TakesScreenshot tks = (TakesScreenshot)BaseClass.sdriver;
		 String src = tks.getScreenshotAs(OutputType.BASE64);
		 String currentTime = new Date().toString().replace(" ", "_").replace(":", "_");
//		 File des = new File("./ScreenShot/"+testName+currentTime +".png");
//		 try {
//			FileHandler.copy(src, des);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
		test.addScreenCaptureFromBase64String(src, testName+"_"+currentTime);	  
		test.log(Status.FAIL,result.getMethod().getMethodName() + "<=====FAILS======>" );
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
	
	
	
}
