package com.comcast.crm.Groupexecution;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutiility.JavaUtility;
import com.comcast.crm.org.objectrepository.CreateOrganizationPage;
import com.comcast.crm.org.objectrepository.HomePage;
import com.comcast.crm.org.objectrepository.OrganizationInfoPage;
import com.comcast.crm.org.objectrepository.OrganizationPage;

public class CreateOrgSingleGroupExecutionTest extends BaseClassSingleGroupExecution  {
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
	@Test(groups = "Regression Test")
	public void createOrg() throws Throwable {

// read TestScript data from Excel File for Organization
		String orgName    = excelUtility.getDataFromExcelString("org", 1, 2) + javaUtility.getRandomNumber();
		String shipAddress= excelUtility.getDataFromExcelString("org", 1, 5);

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
		String actualOrgName = organizationInfoPage.getOrgNameVerify().getText();
		if (actualOrgName.equals(orgName)) {
			System.out.println(orgName + ": OrgName is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": OrgName is not Matched " + " Status = FAIL");
		}

		// verification for shipaddress
		String actualShipAddress = organizationInfoPage.getShippingAddressVerify().getText();
		if (actualShipAddress.equals(shipAddress)) {
			System.out.println(shipAddress + ": Shipaddress is Matched " + " Status = PASS");
		} else {
			System.out.println(shipAddress + ": Shipaddress is not Matched " + " Status = FAIL");
		}

	}

//  Create Organization With	Phone Number:
	@Test(groups = "Regression Test")
	public void createOrgWithPhoneNumber() throws EncryptedDocumentException, IOException {

		String orgName    = excelUtility.getDataFromExcelString("org", 1, 2) + javaUtility.getRandomNumber();
		String shipAddress= excelUtility.getDataFromExcelString("org", 1, 5);
		String phoneNumber= excelUtility.getDataFromExcelString("org", 1, 6);

		HomePage homePage = new HomePage(driver);
		// click on organization
		homePage.getOrgTagLink().click();

		// click on create organization + button
		OrganizationPage organizationPage = new OrganizationPage(driver);
		organizationPage.getCreateorganizationbtn().click();

		// send the details to organization name
		CreateOrganizationPage createorganizationpage = new CreateOrganizationPage(driver);
		createorganizationpage.sendOrgName(orgName);

		// send the details to Shipping address
		createorganizationpage.sendShippingAddress(shipAddress);

		// send the details to Shipping address
		createorganizationpage.sendPhoneNumber(phoneNumber);

		// save the data by save button
		createorganizationpage.getSaveBtn().click();

		// verification for Header
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		String actualHeaderInfo = organizationInfoPage.getHeaderInfoVerify().getText();
		if (actualHeaderInfo.contains(orgName)) {
			System.out.println(orgName + ": Headerinfo is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": Headerinfo is not Matched " + " Status = FAIL");
		}

		// verification for Org Name
		String actualOrgName = organizationInfoPage.getOrgNameVerify().getText();
		if (actualOrgName.equals(orgName)) {
			System.out.println(orgName + ": OrgName is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": OrgName is not Matched " + " Status = FAIL");
		}
		
		// verification for Phone Number
        String actualPhoneNumber = organizationInfoPage.getPhoneNameVerify().getText();
        	if (actualPhoneNumber.equals(phoneNumber)) {
        		System.out.println(phoneNumber + ": phoneNumber is Matched " + " Status = PASS");
        	} else {
        		System.out.println(phoneNumber + ": phoneNumber is not Matched " + " Status = FAIL");
        	}
        
		// verification for Ship Address
		String actualShipAddress = organizationInfoPage.getShippingAddressVerify().getText();
		if (actualShipAddress.equals(shipAddress)) {
			System.out.println(shipAddress + ": Shipaddress is Matched " + " Status = PASS");
		} else {
			System.out.println(shipAddress + ": Shipaddress is not Matched " + " Status = FAIL");
		}
	}

//  Create Organization With	Industry and Type DropDown:	
	@Test (groups = "Smoke Test")
	public void createOrgWithIndustriesAndType() throws EncryptedDocumentException, IOException {

		String orgName    = excelUtility.getDataFromExcelString("org", 1, 2) + javaUtility.getRandomNumber();
		String industryDD = excelUtility.getDataFromExcelString("org", 1, 3);
		String typeDD     = excelUtility.getDataFromExcelString("org", 1, 4);
		String shipAddress= excelUtility.getDataFromExcelString("org", 1, 5);

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
		createorganizationpage.sendIndustryDD(industryDD);

		// Verification for Industry
        System.out.println("<=================All Options Available in Industry DropDown=================>");
        createorganizationpage.getOptionsIndustryDD();
        System.out.println("<=================All Options Printed in Industry DropDown===================>");
        System.out.println("<=================Industry DropDown Verification Done===================>");
		
		// select drop down for type
		createorganizationpage.sendTypeDD(typeDD);
		
        // Verification for Type
        System.out.println("<===================All Options Available in Type DropDown====================>");
        createorganizationpage.getOptionsTypeDD();
        System.out.println("<===================All Options Printed in Type DropDown======================>");
        System.out.println("<=================Type DropDown Verification Done===================>");
        
		// send the details to Shipping address
		createorganizationpage.sendShippingAddress(shipAddress);

		// save the data by save button
		createorganizationpage.getSaveBtn().click();
		
		// Verification for header
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		String actualHeaderInfo = organizationInfoPage.getHeaderInfoVerify().getText();
		if (actualHeaderInfo.contains(orgName)) {
			System.out.println(orgName + ": Headerinfo is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": Headerinfo is not Matched " + " Status = FAIL");
		}

		// Verification for orgname
		String actualOrgName = organizationInfoPage.getOrgNameVerify().getText();
		if (actualOrgName.equals(orgName)) {
			System.out.println(orgName + ": OrgName is Matched " + " Status = PASS");
		} else {
			System.out.println(orgName + ": OrgName is not Matched " + " Status = FAIL");
		}
	
		// Verification for Shipaddress
		String actualShipAddress = organizationInfoPage.getShippingAddressVerify().getText();
		if (actualShipAddress.equals(shipAddress)) {
			System.out.println(shipAddress + ": Shipaddress is Matched " + " Status = PASS");
		} else {
			System.out.println(shipAddress + ": Shipaddress is not Matched " + " Status = FAIL");
		}
	}
}
