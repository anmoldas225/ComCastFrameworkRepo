package com.comcast.crm.org.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	WebDriver driver;
	
	public OrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// click on create organization + button
	@FindBy(xpath="//img[@alt='Create Organization...']")
	 private WebElement createorganizationbtn;

	public WebElement getCreateorganizationbtn() {
		return createorganizationbtn;
	}
	
	

}
