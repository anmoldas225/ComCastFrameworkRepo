package com.comcast.crm.org.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	public ContactInfoPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	// verification for Header
	@FindBy(className="dvHeaderText")
	private WebElement verifyHeaderInfo;
	
	public WebElement getVerifyHeaderInfo() {
		return verifyHeaderInfo;
	}
	// verification for Last Name
	@FindBy(id="mouseArea_Last Name")
	private WebElement verifyLastNameInfo;
	
	public WebElement getVerifyLastNameInfo() {
		return verifyLastNameInfo;
	}
	
	//verification for Start Date.
	@FindBy(id="dtlview_Support Start Date")
	private WebElement verifyStartDateInfo;
	
	public WebElement getVerifyStartDateInfo() {
		return verifyStartDateInfo;
	}
	//verification for End Date.
	@FindBy(id="dtlview_Support End Date")
	private WebElement verifyEndDateInfo;
	
	public WebElement getVerifyEndDateInfo() {
		return verifyEndDateInfo;
	}

}
