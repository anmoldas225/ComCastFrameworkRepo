package com.comcast.crm.baseTest;
 
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseutility.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutiility.JavaUtility;
import com.comcast.crm.generic.webdriverutiility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;
import com.comcast.crm.org.objectrepository.ContactInfoPage;
import com.comcast.crm.org.objectrepository.ContactOrganizationSearchPage;
import com.comcast.crm.org.objectrepository.ContactPage;
import com.comcast.crm.org.objectrepository.CreateContactPage;
import com.comcast.crm.org.objectrepository.CreateOrganizationPage;
import com.comcast.crm.org.objectrepository.HomePage;
import com.comcast.crm.org.objectrepository.OrganizationInfoPage;
import com.comcast.crm.org.objectrepository.OrganizationPage;

public class CreateContactWithBaseClassTest extends BaseClass  {
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
	@Test
	public void createContactWithLastName() throws EncryptedDocumentException, IOException {

		String lastName = excelUtility.getDataFromExcelString("contact", 4, 3) + javaUtility.getRandomNumber();

		UtilityClassObject.getTest().log(Status.INFO, "Home Page is Displayed");
		// click on contact module header
		HomePage homePage= new HomePage(driver);
		homePage.getContactTagLink().click();

		UtilityClassObject.getTest().log(Status.INFO, "Contact Page is Displayed");
		// click on add or + contact module button
		ContactPage contactPage= new ContactPage(driver);
		contactPage.getCreateContactBtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Create Contact Page is Displayed");
		// Fill last Name TextField
		CreateContactPage createContactPage= new CreateContactPage(driver);
		createContactPage.getLastName().sendKeys(lastName);

		UtilityClassObject.getTest().log(Status.INFO, "Save Button is Clicked");
		// save the data by save button
		createContactPage.getSaveBtn().click();

		// verification for Last Name
		ContactInfoPage contactInfoPage  = new ContactInfoPage(driver);
		String actualLastName = contactInfoPage.getVerifyLastNameInfo().getText();
		boolean status = actualLastName.contains(lastName);
		Assert.assertEquals(status, true,"Last Name is verified");

	}
		
//  Create Contact with Organization		
	@Test
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
		boolean status =actualheaderinfo.contains(orgName);
		Assert.assertEquals(status, true, "Organization Name is verified");

		
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
		SoftAssert sassert = new SoftAssert();
		sassert.assertNotEquals(actualHeaderInfo1, lastName, "Header Info1 is verified");
		sassert.assertAll();
		
		// verification for Last Name
		String actualLastName = contactInfoPage.getVerifyLastNameInfo().getText();
		Assert.assertEquals(actualLastName,lastName,"Last Name is verified");

		//dynamic locater write in Test Script only because POM file do not support dynamic Element
		// verification for Org Name.
		String actualOrgName = driver.findElement(By.linkText(orgName)).getText();
		Assert.assertEquals(actualOrgName,orgName,"Organization Name is verified");

	}

//  Create Contact with Start Date and End Date
	@Test
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
		Assert.assertEquals(actualLastName,lastName,"Last Name is verified");
		
		// verification for start date.
		String actualStartDate = contactInfoPage.getVerifyStartDateInfo().getText();
		Assert.assertEquals(actualStartDate,actDate,"Actual Date is verified");

		// verification for end date.
		String actualEndDate = contactInfoPage.getVerifyEndDateInfo().getText();
		Assert.assertEquals(actualEndDate,dateRequired,"End Date is verified");
	}
}
