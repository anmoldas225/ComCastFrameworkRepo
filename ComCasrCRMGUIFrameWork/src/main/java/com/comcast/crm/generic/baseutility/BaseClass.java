package com.comcast.crm.generic.baseutility;

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
import com.comcast.crm.generic.webdriverutiility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;
import com.comcast.crm.org.objectrepository.HomePage;
import com.comcast.crm.org.objectrepository.LoginPage;

public class BaseClass {
	
	public DataBaseUtility databaseUtility = new DataBaseUtility();
	public FileUtility fileUtility = new FileUtility();
	public WebDriver driver;
	public static WebDriver sdriver;
    public ExtentReports report;
	
    @BeforeSuite
	public void configBS() {
		System.out.println("Database Connection or Report Creation");
	}
   
	@BeforeClass
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
		 sdriver=driver;
		 
		 UtilityClassObject.setDriver(driver);
		 
	}
	
	@BeforeMethod
	public void configBM() throws Throwable {
		
		System.out.println("Login to Application");
		String URL      = fileUtility.getDataFromPropertiesFile("url");
		String USERNAME = fileUtility.getDataFromPropertiesFile("un");
		String PASSWORD = fileUtility.getDataFromPropertiesFile("pwd");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(20, URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod
	public void configAM() {
		
		System.out.println("log Out");
		HomePage homePage = new HomePage(driver);
		homePage.signOut();
	}
	
	@AfterClass
	public void configAC() {
		
		System.out.print("Close the Browser");
		WebDriverUtility webDriverUtility = new WebDriverUtility(driver);
		webDriverUtility.quit();
	} 
	
	@AfterSuite
	public void configAS() throws SQLException {
		
		System.out.print("Close the database or Report Backup");
		//databaseUtility.closeDbconnection();
	}
	
}
