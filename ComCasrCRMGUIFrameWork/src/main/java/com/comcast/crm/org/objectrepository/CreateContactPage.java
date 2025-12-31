package com.comcast.crm.org.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;

public class CreateContactPage {

	WebDriver driver;
	WebDriverUtility webdriverUtility;

	public CreateContactPage(WebDriver driver) {
		this.driver = driver;
		this.webdriverUtility= new WebDriverUtility(driver);
		PageFactory.initElements(driver, this);
	}

	//send lastname detail to textfield.
	@FindBy(name="lastname")
	private WebElement lastName;
	
	public WebElement getLastName() {
		return lastName;
	}
	
	//send Org detail to textfield.
	@FindBy(xpath="//img[@alt ='Select']")
	private WebElement orgNameSel;

	public WebElement getOrgNameSel() {
		return orgNameSel;
	}
	
	//set start date field 
	@FindBy(name="support_start_date")
	private WebElement supportStartDate;
	
	public WebElement getSupportStartDate() {
		return supportStartDate;
	}

	//set end date field
	@FindBy(name="support_end_date")
	private WebElement supportEndDate;
	
	public WebElement getSupportEndDate() {
		return supportEndDate;
	}
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
}
