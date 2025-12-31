package com.comcast.crm.Parallelexecution;
 
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutiility.JavaUtility;
import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;
import com.comcast.crm.org.objectrepository.ContactInfoPage;
import com.comcast.crm.org.objectrepository.ContactOrganizationSearchPage;
import com.comcast.crm.org.objectrepository.ContactPage;
import com.comcast.crm.org.objectrepository.CreateContactPage;
import com.comcast.crm.org.objectrepository.CreateOrganizationPage;
import com.comcast.crm.org.objectrepository.HomePage;
import com.comcast.crm.org.objectrepository.OrganizationInfoPage;
import com.comcast.crm.org.objectrepository.OrganizationPage;

public class CreateContactDistributedExecutionTest extends BaseClassParallelDistributedExecution  {
//	Utility Files objects:
	ExcelUtility      excelUtility    = new ExcelUtility();
	JavaUtility       javaUtility     = new JavaUtility();	

	/*IMPORTANT POINT TO OBSERVE:Never Declare Any Utility and POM file object
	 * globally in @Test annotation when you create their constructor with "driver"
	 * argument. Because When you run "TestNG Test". TestNG look for @Test and run
	 * it. But driver did not load until @BeforeMethod run. Due to Which driver
	 * always load early and get null in driver. Due to this reason we will get
	 * "org.testng.TestNGException"*/	
	
//  Create Contact with last Name	
	@Test(groups = "Regression Test")
	public void createContactWithLastName() throws EncryptedDocumentException, IOException {

		String lastName = excelUtility.getDataFromExcelString("contact", 4, 3) + javaUtility.getRandomNumber();

		// click on contact module header
		HomePage homePage= new HomePage(driver);
		homePage.getContactTagLink().click();

		// click on add or + contact module button
		ContactPage contactPage= new ContactPage(driver);
		contactPage.getCreateContactBtn().click();

		// Fill last Name TextField
		CreateContactPage createContactPage= new CreateContactPage(driver);
		createContactPage.getLastName().sendKeys(lastName);

		// save the data by save button
		createContactPage.getSaveBtn().click();

		// verification for Last Name
		ContactInfoPage contactInfoPage  = new ContactInfoPage(driver);
		String actualLastName = contactInfoPage.getVerifyLastNameInfo().getText();
		if (actualLastName.contains(lastName)) {
			System.out.println(lastName + ": lastName is Matched " + " Status = PASS");
		} else {
			System.out.println(lastName + ": lastName is not Matched " + " Status = FAIL");
		}

	}
		
//  Create Contact with Organization		
	@Test(groups = "Smoke Test")
	public void createContactWithOrg() throws Throwable {

//Test Script Data	

// generate random number and we can use it in to generate multiple username without changing it in excel file;
// read TestScript data from Excel File to create pre organization condition and contact module.
		String orgName    = excelUtility.getDataFromExcelString("contact", 4, 2)+javaUtility.getRandomNumber();
		String lastName   = excelUtility.getDataFromExcelString("contact", 4, 3)+javaUtility.getRandomNumber();
		String shipAddress= excelUtility.getDataFromExcelString("contact", 4, 4);

		// Precondition for adding organization

		// click on organization
		HomePage homePage= new HomePage(driver);
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
		String actualheaderinfo = organizationInfoPage.getHeaderInfoVerify().getText();
		if (actualheaderinfo.contains(orgName)) {
			System.out.println(orgName + ": Headerinfo is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": Headerinfo is not Matched " + " Status = FAIL");
		}

		// navigate to contact module.

		// click on contact module header
		homePage.getContactTagLink().click();

		// click on add or + contact module button
		ContactPage contactPage= new ContactPage(driver);
		contactPage.getCreateContactBtn().click();

		// Fill last Name TextField
		CreateContactPage createContactPage= new CreateContactPage(driver);
		createContactPage.getLastName().sendKeys(lastName);

		// select orgname name TextField.
		createContactPage.getOrgNameSel().click();

		// switch to child window to select orgname
		WebDriverUtility  webdriverUtility= new WebDriverUtility(driver);
		webdriverUtility.switchtowindowonURL("module=Accounts");

		// send detail to search textfield.
		ContactOrganizationSearchPage contactOrganizationSearchPage = new ContactOrganizationSearchPage(driver);
		contactOrganizationSearchPage.getSearchText().sendKeys(orgName);

		// hit search now button.
		contactOrganizationSearchPage.getSearchBtn().click();

		//dynamic locater write in Test Script only because POM file do not support dynamic Element
		// select the Orgname
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click(); 

		// switch to parent window to perform action
		webdriverUtility.switchtowindowonURL("Contacts&action");

		// save the data by save button
		createContactPage.getSaveBtn().click();

		// verification for header with last Name
		ContactInfoPage contactInfoPage= new ContactInfoPage(driver);
		String actualHeaderInfo1 = contactInfoPage.getVerifyHeaderInfo().getText();
		if (actualHeaderInfo1.contains(lastName)) {
			System.out.println(lastName + ": Headerinfo is Matched " + " Status = PASS");
		} else {
			System.out.println(lastName + ": Headerinfo is not Matched " + " Status = FAIL");
		}

		// verification for Last Name
		String actualLastName = contactInfoPage.getVerifyLastNameInfo().getText();
		if (actualLastName.contains(lastName)) {
			System.out.println(lastName + ": lastName is Matched " + " Status = PASS");
		} else {
			System.out.println(lastName + ": lastName is not Matched " + " Status = FAIL");
		}

		//dynamic locater write in Test Script only because POM file do not support dynamic Element
		// verification for Org Name.
		String actualorgname = driver.findElement(By.linkText(orgName)).getText();
	    if (actualorgname.equals(orgName)) {
			System.out.println(orgName + ": OrgName is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": OrgName is not Matched " + " Status = FAIL");
		}

	}

//  Create Contact with Start Date and End Date
	@Test(groups = "Regression Test")
	public void createContactWithStartAndEndDate() throws EncryptedDocumentException, IOException {

		String lastName = excelUtility.getDataFromExcelString("contact", 4, 3)+javaUtility.getRandomNumber();

		// click on contact module header
		HomePage homePage= new HomePage(driver);
		homePage.getContactTagLink().click();

		// click on add or + contact module button
		ContactPage contactPage= new ContactPage(driver);
		contactPage.getCreateContactBtn().click();

		// Fill last Name TextField
		CreateContactPage createContactPage= new CreateContactPage(driver);
		createContactPage.getLastName().sendKeys(lastName);

		// set start and end date
		String actDate     = javaUtility.getSystemDateYYYYMMDD();
		String dateRequired= javaUtility.getRequiredDateYYYYMMDD(30);

		// set start date field
		createContactPage.getSupportStartDate().clear();
		createContactPage.getSupportStartDate().sendKeys(actDate);

		// set end date field
		createContactPage.getSupportEndDate().clear();
		createContactPage.getSupportEndDate().sendKeys(dateRequired);
		
		// save the data by save button
		createContactPage.getSaveBtn().click();

		// verification for Last Name
		ContactInfoPage contactInfoPage= new ContactInfoPage(driver);
		String actualLastName = contactInfoPage.getVerifyLastNameInfo().getText();
		if (actualLastName.contains(lastName)) {
			System.out.println(lastName + ": lastName is Matched " + " Status = PASS");
		} else {
			System.out.println(lastName + ": lastName is not Matched " + " Status = FAIL");
		}
		// verification for start date.
		String actualStartDate = contactInfoPage.getVerifyStartDateInfo().getText();
		if (actualStartDate.equals(actDate)) {
			System.out.println(actDate + ": actual date is Matched " + " Status = PASS");
		} else {
			System.out.println(actDate + ": actual date is not Matched " + " Status = FAIL");
		}

		// verification for end date.
		String actualEndDate = contactInfoPage.getVerifyEndDateInfo().getText();
		if (actualEndDate.equals(dateRequired)) {
			System.out.println(dateRequired + ": daterequired is Matched " + " Status = PASS");
		} else {
			System.out.println(dateRequired + ": daterequired is not Matched " + " Status = FAIL");
		}
	}
}
