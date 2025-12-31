package com.comcast.crm.org.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	public OrganizationInfoPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className ="dvHeaderText")
	  private WebElement headerInfoVerify;

	public WebElement getHeaderInfoVerify() {
		return headerInfoVerify;
	}
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgNameVerify;

	public WebElement getOrgNameVerify() {
		return orgNameVerify;
	}
	
	@FindBy(id="dtlview_Phone")
	private WebElement phoneNumberVerify;
	
	public WebElement getPhoneNameVerify() {
		return phoneNumberVerify;
	}
	
	@FindBy(id="dtlview_Shipping Address")
	private WebElement shippingAddressVerify ;

	public WebElement getShippingAddressVerify() {
		return shippingAddressVerify;
	}


}
