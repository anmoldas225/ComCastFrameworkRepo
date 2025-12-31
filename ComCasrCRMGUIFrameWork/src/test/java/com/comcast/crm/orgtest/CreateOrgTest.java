package com.comcast.crm.orgtest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutiility.JavaUtility;
import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;
import com.comcast.crm.org.objectrepository.CreateOrganizationPage;
import com.comcast.crm.org.objectrepository.HomePage;
import com.comcast.crm.org.objectrepository.LoginPage;
import com.comcast.crm.org.objectrepository.OrganizationInfoPage;
import com.comcast.crm.org.objectrepository.OrganizationPage;

public class CreateOrgTest {

	@Test
	public void createorg() throws Throwable {

		FileUtility      fileUtility  = new FileUtility(); // Properties File
		ExcelUtility     excelUtility = new ExcelUtility();// Excel File
		JavaUtility      javaUtility  = new JavaUtility(); // Java File
		WebDriverUtility weddriverUtility;                 // WebDriverUtility File

		// read common data from properties file.
		String BROWSER  = fileUtility.getDataFromPropertiesFile("bro");
		String URL      = fileUtility.getDataFromPropertiesFile("url");
		String USERNAME = fileUtility.getDataFromPropertiesFile("un");
		String PASSWORD = fileUtility.getDataFromPropertiesFile("pwd");

		// generate random number and we can use it in to generate multiple
		// username without changing it in excel file;
		// Random random = new Random();
		// int randomint = random.nextInt(1000);

		// read TestScript data from Excel File for Organization
		String orgName     = excelUtility.getDataFromExcelString("org", 1, 2) + javaUtility.getRandomNumber();
		String industryDD  = excelUtility.getDataFromExcelString("org", 1, 3);
		String typeDD      = excelUtility.getDataFromExcelString("org", 1, 4);
		String shipAddress = excelUtility.getDataFromExcelString("org", 1, 5);

		WebDriver driver = null;

		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		weddriverUtility = new WebDriverUtility(driver); // WebDriverUtility File
		// maximize window
		weddriverUtility.maximize();
		// implicitly Wait
		weddriverUtility.waitforpagetoload(20);
		// Launch The Browser with website.
		weddriverUtility.get(URL);

//		//Provide the details to USERNAME and PASSWORD TEXTFIELD.
//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		//click on button
//		driver.findElement(By.id("submitButton")).click();

		// LOGIN PAGE Procedure POM IMPLEMENTATION
		LoginPage loginPage = new LoginPage(driver);
		// login into Vtiger and get Home Page
		loginPage.login();

//      HOME  PAGE PROCEDURE POM IMPLEMENTATION
		HomePage homePage = new HomePage(driver);
		// click on organization
		homePage.getOrgTagLink().click();
		
		// click on create organization + button
		OrganizationPage organizationPage = new OrganizationPage(driver);
		organizationPage.getCreateorganizationbtn().click();
		
		// send the details to organization name
		CreateOrganizationPage createorganizationpage = new CreateOrganizationPage(driver);
		createorganizationpage.sendOrgName(orgName);

		// select drop down for industry
		//WebElement indusDD = driver.findElement(By.xpath("//select[@name='industry']"));
		//weddriverutility.selectbyIndex(indusDD, 1);
		createorganizationpage.sendIndustryDD(industryDD);
		
		// select drop down for type
		//WebElement typeDD = driver.findElement(By.xpath("//select[@name='accounttype']"));
		//weddriverutility.selectbyVisibleText(typeDD, "Analyst");
		createorganizationpage.sendTypeDD(typeDD);
	
		// send the details to Shipping address
		createorganizationpage.sendShippingAddress(shipAddress);

		// save the data by save button
		createorganizationpage.getSaveBtn().click();

		// verification for header
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		String actualHeaderInfo = organizationInfoPage.getHeaderInfoVerify().getText();
		if (actualHeaderInfo.contains(orgName)) {
			System.out.println(orgName + ": Headerinfo is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": Headerinfo is not Matched " + " Status = FAIL");
		}

		// verification for orgname
		String actualOrgName=organizationInfoPage.getOrgNameVerify().getText();
		if (actualOrgName.equals(orgName)) {
			System.out.println(orgName + ": OrgName is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": OrgName is not Matched " + " Status = FAIL");
		}
		
		
		// verification for shipaddress
		String actualShipAddress=organizationInfoPage.getShippingAddressVerify().getText();
		if (actualShipAddress.equals(shipAddress)) {
			System.out.println(shipAddress + ": Shipaddress is Matched " + " Status = PASS");
		} else {
			System.out.println(shipAddress + ": Shipaddress is not Matched " + " Status = FAIL");
		}
 
		// hover to adminstrator icon
		// Apply Explicit wait for Synchronization
		// click on Signout link
		homePage.signOut();

		// quit the browser or close all the window.
		weddriverUtility.quit();
        
	}

}
