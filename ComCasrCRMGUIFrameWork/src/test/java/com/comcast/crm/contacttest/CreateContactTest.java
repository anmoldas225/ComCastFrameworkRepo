package com.comcast.crm.contacttest;
 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutiility.JavaUtility;
import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;
import com.comcast.crm.org.objectrepository.ContactOrganizationSearchPage;
import com.comcast.crm.org.objectrepository.ContactPage;
import com.comcast.crm.org.objectrepository.CreateContactPage;
import com.comcast.crm.org.objectrepository.CreateOrganizationPage;
import com.comcast.crm.org.objectrepository.HomePage;
import com.comcast.crm.org.objectrepository.LoginPage;
import com.comcast.crm.org.objectrepository.OrganizationInfoPage;
import com.comcast.crm.org.objectrepository.OrganizationPage;

public class CreateContactTest {
	
	@Test
	public void createcont() throws Throwable {

		FileUtility  fileUtility  = new FileUtility(); //Properties File
		ExcelUtility excelUtility = new ExcelUtility();//Excel File
		JavaUtility  javaUtility  = new JavaUtility(); //Java File
		WebDriverUtility webdriverUtility;             //WebDriverUtility File
		
		
		// read common data from properties file.
		String BROWSER  = fileUtility.getDataFromPropertiesFile("bro");
		String URL      = fileUtility.getDataFromPropertiesFile("url");
		String USERNAME = fileUtility.getDataFromPropertiesFile("un");
		String PASSWORD = fileUtility.getDataFromPropertiesFile("pwd");
       
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
		
		webdriverUtility = new WebDriverUtility(driver); //WebDriverUtility File
	    //maximize window
		webdriverUtility.maximize();
		//implicitly Wait
		webdriverUtility.waitforpagetoload(20);
		//Launch The Browser with website.
		webdriverUtility.get(URL);


//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);

//		driver.findElement(By.id("submitButton")).click();
		
		//POM IMPLEMENTATION OF THE LOGIN
		LoginPage loginPage = new LoginPage(driver);
		//Provide the details to USERNAME and PASSWORD TEXTFIELD.
		//click on button
		loginPage.login();

		// generate random number and we can use it in to generate multiple username without changing it in excel file;
		// read TestScript data from Excel File to create pre organization condition and contact module.
		String orgName      = excelUtility.getDataFromExcelString("contact",4, 2) + javaUtility.getRandomNumber(); 
		String lastName     = excelUtility.getDataFromExcelString("contact",4, 3) + javaUtility.getRandomNumber();
		String shipAddress  = excelUtility.getDataFromExcelString("contact",4, 4);

	    //Precondition for adding organization
		
		HomePage homePage = new HomePage(driver);
		// click on organization
		homePage.getOrgTagLink().click();
		
		// click on create organization + button
		OrganizationPage organizationPage = new OrganizationPage(driver);
		organizationPage.getCreateorganizationbtn().click();
		
		// send the details to organization name
		CreateOrganizationPage createOrganizationPage = new CreateOrganizationPage(driver);
		createOrganizationPage.getOrganizationName().sendKeys(orgName);

		// send the details to shipping name
		createOrganizationPage.getShippingAddress().sendKeys(shipAddress);

		// save the data by save button
		createOrganizationPage.getSaveBtn().click();
		
		// verification for header
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		String actualheaderinfo=organizationInfoPage.getHeaderInfoVerify().getText();
		if (actualheaderinfo.contains(orgName)) {
			System.out.println(orgName + ": Headerinfo is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": Headerinfo is not Matched " + " Status = FAIL");
		}
		
		//navigate to contact module.
		
		//click on contact module header
		homePage.getContactTagLink().click();

		//click on add or + contact module button 
		ContactPage contactPage = new ContactPage(driver);
		contactPage.getCreateContactBtn().click();
	
		//Fill last Name TextField
		CreateContactPage createContactPage = new CreateContactPage(driver);
		createContactPage.getLastName().sendKeys(lastName);
	
		//select orgname name TextField.
		createContactPage.getOrgNameSel().click();
		
		//switch to child window to select orgname
		webdriverUtility.switchtowindowonURL("module=Accounts");
		
		//send detail to search textfield.
		ContactOrganizationSearchPage contactOrganizationSearchPage = new ContactOrganizationSearchPage(driver);
		contactOrganizationSearchPage.getSearchText().sendKeys(orgName);
		
	    //hit search now button.
		contactOrganizationSearchPage.getSearchBtn().click();
	
		//select the Orgname
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		// switch to parent window to perform action
		webdriverUtility.switchtowindowonURL("Contacts&action");		
		
		//set start and end date
		String actDate = javaUtility.getSystemDateYYYYMMDD();
		
		String dateRequired = javaUtility.getRequiredDateYYYYMMDD(30);
		
		//set start date field 
		createContactPage.getSupportStartDate().clear();
		createContactPage.getSupportStartDate().sendKeys(actDate);
		
		//set end date field 
		createContactPage.getSupportEndDate().clear();
		createContactPage.getSupportEndDate().sendKeys(dateRequired);
	
		//save the data by save button
		createContactPage.getSaveBtn().click();
		
		// verification for header
		actualheaderinfo = driver.findElement(By.className("dvHeaderText")).getText();
		if (actualheaderinfo.contains(orgName)) {
			System.out.println(orgName + ": Headerinfo is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": Headerinfo is not Matched " + " Status = FAIL");
		}
		
		// verification for orgname.
		String actualorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actualorgname.equals(orgName)) {
			System.out.println(orgName + ": OrgName is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": OrgName is not Matched " + " Status = FAIL");
		}
		
		//verification for start date.
	    String actualstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
	    if (actualstartdate.equals(actDate)) {
		System.out.println(actDate + ": actual date is Matched " + " Status = PASS");
		} else {
		  System.out.println(actDate + ": actual date is not Matched " + " Status = FAIL");
		}

		//verification for end date.
		String actualendate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (actualendate.equals(dateRequired)) {
			System.out.println(dateRequired + ": daterequired is Matched " + " Status = PASS");
		} else {
			System.out.println(dateRequired + ": daterequired is not Matched " + " Status = FAIL");
		}
		
		//hover to adminstrator icon
		//Apply Explicit wait for Synchronization
		//click on Signout link
        homePage.signOut(); 
		
        
		//quit the browser or close all the window.
		webdriverUtility.quit();

	}
	

}
