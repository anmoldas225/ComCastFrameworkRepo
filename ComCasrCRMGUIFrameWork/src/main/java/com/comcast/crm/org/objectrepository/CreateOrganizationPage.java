package com.comcast.crm.org.objectrepository;



import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;

public class CreateOrganizationPage {

	WebDriver driver;
	WebDriverUtility webdriverUtility;

	public CreateOrganizationPage(WebDriver driver) {
		this.driver = driver;
		this.webdriverUtility= new WebDriverUtility(driver);
		PageFactory.initElements(driver, this);
	}

	// send the details to organization name
	@FindBy(name = "accountname")
	private WebElement organizationName;

	public WebElement getOrganizationName() {
		return organizationName;
	}
	
	@FindBy(id="phone")
	private WebElement phoneNumber;

	public WebElement getPhoneName() {
		return phoneNumber;
	}
	
	// select drop down for industry
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industryDD;

	public WebElement getIndustryDD() {
		return industryDD;
	}

	// select drop down for type
	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement typeDD;

	public WebElement getTypeDD() {
		return typeDD;
	}

	// send the details to Shipping address
	@FindBy(xpath = "//textarea[@name='ship_street']")
	private WebElement shippingAddress;

	public WebElement getShippingAddress() {
		return shippingAddress;
	}

	// save the data by save button
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void sendOrgName(String orgName) {
		organizationName.sendKeys(orgName);
	}
	
	public void sendPhoneNumber(String phoneNo) {
		phoneNumber.sendKeys(phoneNo);
	}
	
	public void sendIndustryDD(String visibleText) {
	   webdriverUtility.selectbyVisibleText(industryDD, visibleText);
	}
	
	public void sendTypeDD(String visibleText) {
		webdriverUtility.selectbyVisibleText(typeDD, visibleText);
	}
	
	public void sendShippingAddress(String shipAddress) {
		shippingAddress.sendKeys(shipAddress);
	}
	
	public void getOptionsIndustryDD() {
		
		List<String> Industry = webdriverUtility.getOptions(industryDD);
		int i = 0;
		System.out.println("Industry Lists is below");
		for(String dropDownOptions:Industry) {
			System.out.print(i++ +".");
			System.out.println(dropDownOptions); 
		}
	}
	
    public void getOptionsTypeDD() {
		
		List<String> Type = webdriverUtility.getOptions(typeDD);
		int i = 0;
		System.out.println("Type Lists is below");
		for(String dropDownOptions:Type) {
			System.out.print(i++ +".");
			System.out.println(dropDownOptions); 
		}
	}
}
