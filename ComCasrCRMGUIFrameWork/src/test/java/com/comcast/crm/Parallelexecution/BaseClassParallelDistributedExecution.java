package com.comcast.crm.Parallelexecution;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;
import com.comcast.crm.org.objectrepository.HomePage;
import com.comcast.crm.org.objectrepository.LoginPage;

public class BaseClassParallelDistributedExecution  {
	
	public DataBaseUtility databaseUtility = new DataBaseUtility();
	public FileUtility fileUtility = new FileUtility();
	public WebDriver driver;
    public ExtentReports report;
	
	@BeforeSuite(groups = {"Smoke Test","Regression Test"})
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
   
	@BeforeClass(groups = {"Smoke Test","Regression Test"})
	public void configBC() throws Throwable{
		
		System.out.println("Launch Browser");
		String BROWSER=fileUtility.getDataFromPropertiesFile("bro");
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
	}
	
	@BeforeMethod(groups = {"Smoke Test","Regression Test"})
	public void configBM() throws Throwable {
		
		System.out.println("Login to Application");
		String URL      = fileUtility.getDataFromPropertiesFile("url");
		String USERNAME = fileUtility.getDataFromPropertiesFile("un");
		String PASSWORD = fileUtility.getDataFromPropertiesFile("pwd");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(20, URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"Smoke Test","Regression Test"})
	public void configAM() {
		
		System.out.println("log Out");
		HomePage homePage = new HomePage(driver);
		homePage.signOut();
	}
	
	@AfterClass(groups = {"Smoke Test","Regression Test"})
	public void configAC() {
		
		System.out.print("Close the Browser");
		WebDriverUtility webDriverUtility = new WebDriverUtility(driver);
		webDriverUtility.close();
	} 
	
	@AfterSuite(groups = {"Smoke Test","Regression Test"})
	public void configAS() throws SQLException {
		
		System.out.print("Close the database or Report Backup");
		report.flush();
		//databaseUtility.closeDbconnection();
	}
	
}
