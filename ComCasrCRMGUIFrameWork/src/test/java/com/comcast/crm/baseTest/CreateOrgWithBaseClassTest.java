package com.comcast.crm.baseTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseutility.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutiility.JavaUtility;
import com.comcast.crm.generic.webdriverutiility.UtilityClassObject;
import com.comcast.crm.org.objectrepository.CreateOrganizationPage;
import com.comcast.crm.org.objectrepository.HomePage;
import com.comcast.crm.org.objectrepository.OrganizationInfoPage;
import com.comcast.crm.org.objectrepository.OrganizationPage;

@Listeners(com.comcast.crm.generic.listenerutility.ListenerImpClass.class)
public class CreateOrgWithBaseClassTest extends BaseClass {
	/*  All Utilities Class Object Creation:*/	
	FileUtility fileUtility  = new FileUtility(); // Properties File
	ExcelUtility excelUtility= new ExcelUtility();// Excel File
	JavaUtility javaUtility  = new JavaUtility(); // Java File

	/*IMPORTANT POINT TO OBSERVE:Never Declare Any Utility and POM file object
	 * globally in @Test annotation when you create their constructor with "driver"
	 * argument. Because When you run "TestNG Test". TestNG look for @Test and run
	 * it. But driver did not load until @BeforeMethod run. Due to Which driver
	 * always load early and get null in driver. Due to this reason we will get
	 * "org.testng.TestNGException"*/

//  Create Organization:
	@Test
	public void createOrg() throws Throwable {

// read TestScript data from Excel File for Organization
		String orgName    = excelUtility.getDataFromExcelString("org", 1, 2) + javaUtility.getRandomNumber();
		String shipAddress= excelUtility.getDataFromExcelString("org", 1, 5);

//      HOME  PAGE PROCEDURE POM IMPLEMENTATION
		UtilityClassObject.getTest().log(Status.INFO, "Home Page is Displayed");
		HomePage homePage = new HomePage(driver);
		// click on organization
		homePage.getOrgTagLink().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Organization Page is Displayed");
		// click on create organization + button
		OrganizationPage organizationPage = new OrganizationPage(driver);
		organizationPage.getCreateorganizationbtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Create Organization Page is Displayed");
		// send the details to organization name
		CreateOrganizationPage createorganizationpage = new CreateOrganizationPage(driver);
		createorganizationpage.sendOrgName(orgName);

		UtilityClassObject.getTest().log(Status.INFO, "Shipping Address is Filled");
		// send the details to Shipping address
		createorganizationpage.sendShippingAddress(shipAddress);

		UtilityClassObject.getTest().log(Status.INFO, "Save Button is Clicked");
		// save the data by save button
		createorganizationpage.getSaveBtn().click();
		
		// verification for header
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		String actualHeaderInfo = organizationInfoPage.getHeaderInfoVerify().getText();
		boolean status =actualHeaderInfo.contains(orgName);
		Assert.assertEquals(status, true, "Header Info is Verified");

		// verification for orgname
		String actualOrgName = organizationInfoPage.getOrgNameVerify().getText();
		Assert.assertEquals(actualOrgName,orgName, "Organization is Verified");
		
		// verification for shipaddress
		String actualShipAddress = organizationInfoPage.getShippingAddressVerify().getText();
		SoftAssert sassert = new SoftAssert();
		sassert.assertEquals(actualShipAddress, shipAddress, "Shipping Address is Verified");
		sassert.assertAll();

	}

//  Create Organization With	Phone Number:
	@Test
	public void createOrgWithPhoneNumber() throws EncryptedDocumentException, IOException {

		String orgName    = excelUtility.getDataFromExcelString("org", 1, 2) + javaUtility.getRandomNumber();
		String shipAddress= excelUtility.getDataFromExcelString("org", 1, 5);
		String phoneNumber= excelUtility.getDataFromExcelString("org", 1, 6);

		UtilityClassObject.getTest().log(Status.INFO, "Home Page is Displayed");
		HomePage homePage = new HomePage(driver);
		// click on organization
		homePage.getOrgTagLink().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Organization Page is Displayed");
		// click on create organization + button
		OrganizationPage organizationPage = new OrganizationPage(driver);
		organizationPage.getCreateorganizationbtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Create Organization Page is Displayed");
		// send the details to organization name
		CreateOrganizationPage createorganizationpage = new CreateOrganizationPage(driver);
		createorganizationpage.sendOrgName(orgName);

		UtilityClassObject.getTest().log(Status.INFO, "Shipping Address is Filled");
		// send the details to Shipping address
		createorganizationpage.sendShippingAddress(shipAddress);

		UtilityClassObject.getTest().log(Status.INFO, "Phone Number is Filled");
		// send the details to Shipping address
		createorganizationpage.sendPhoneNumber(phoneNumber);

		UtilityClassObject.getTest().log(Status.INFO, "Save Button is Clicked");
		// save the data by save button
		createorganizationpage.getSaveBtn().click();

		// verification for Header
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		String actualHeaderInfo = organizationInfoPage.getHeaderInfoVerify().getText();
		boolean status =actualHeaderInfo.contains(orgName);
		SoftAssert sassert = new SoftAssert();
		sassert.assertEquals(status, true, "Header Info is verified");
		sassert.assertAll();

		// verification for Org Name
		String actualOrgName = organizationInfoPage.getOrgNameVerify().getText();
		Assert.assertEquals(actualOrgName,orgName, "Organization Name is verified");

		// verification for Phone Number
        String actualPhoneNumber = organizationInfoPage.getPhoneNameVerify().getText();
    		Assert.assertEquals(actualPhoneNumber,phoneNumber,"Phone Number is verified");

		// verification for Ship Address
		String actualShipAddress = organizationInfoPage.getShippingAddressVerify().getText();
		Assert.assertEquals(actualShipAddress,shipAddress, "Shipping Address is verified");

	}

//  Create Organization With	Industry and Type DropDown:	
	@Test
	public void createOrgWithIndustriesAndType() throws EncryptedDocumentException, IOException {

		String orgName    = excelUtility.getDataFromExcelString("org", 1, 2) + javaUtility.getRandomNumber();
		String industryDD = excelUtility.getDataFromExcelString("org", 1, 3);
		String typeDD     = excelUtility.getDataFromExcelString("org", 1, 4);
		String shipAddress= excelUtility.getDataFromExcelString("org", 1, 5);

		UtilityClassObject.getTest().log(Status.INFO, "Home Page is Displayed");
		HomePage homePage = new HomePage(driver);
		// click on organization
		homePage.getOrgTagLink().click();

		UtilityClassObject.getTest().log(Status.INFO, "Organization Page is Displayed");
		// click on create organization + button
		OrganizationPage organizationPage = new OrganizationPage(driver);
		organizationPage.getCreateorganizationbtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Create Organization Page is Displayed");
		// send the details to organization name
		CreateOrganizationPage createorganizationpage = new CreateOrganizationPage(driver);
		createorganizationpage.sendOrgName(orgName);

		UtilityClassObject.getTest().log(Status.INFO, "Industry DropDown is Filled");
		// select drop down for industry
		createorganizationpage.sendIndustryDD(industryDD);

		// Verification for Industry
        System.out.println("<=================All Options Available in Industry DropDown=================>");
        createorganizationpage.getOptionsIndustryDD();
        System.out.println("<=================All Options Printed in Industry DropDown===================>");
        System.out.println("<=================Industry DropDown Verification Done===================>");
		
		UtilityClassObject.getTest().log(Status.INFO, "Type DropDown is Filled");
		// select drop down for type
		createorganizationpage.sendTypeDD(typeDD);
		
        // Verification for Type
        System.out.println("<===================All Options Available in Type DropDown====================>");
        createorganizationpage.getOptionsTypeDD();
        System.out.println("<===================All Options Printed in Type DropDown======================>");
        System.out.println("<=================Type DropDown Verification Done===================>");
        
		UtilityClassObject.getTest().log(Status.INFO, "Shipping Address is Filled");
		// send the details to Shipping address
		createorganizationpage.sendShippingAddress(shipAddress);

		UtilityClassObject.getTest().log(Status.INFO, "Save Button is Clicked");
		// save the data by save button
		createorganizationpage.getSaveBtn().click();
		
		// Verification for header
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		String actualHeaderInfo = organizationInfoPage.getHeaderInfoVerify().getText();
		boolean status =actualHeaderInfo.contains(orgName);
		SoftAssert sassert = new SoftAssert();
		sassert.assertEquals(status, true, "Header Info is verified");
		sassert.assertAll();

		// Verification for orgname
		String actualOrgName = organizationInfoPage.getOrgNameVerify().getText();
		Assert.assertEquals(actualOrgName,orgName, "Organization Name is verified");
	
		// Verification for Shipaddress
		String actualShipAddress = organizationInfoPage.getShippingAddressVerify().getText();
		Assert.assertEquals(actualShipAddress,shipAddress, "Shipping Address is verified");

	}
}
