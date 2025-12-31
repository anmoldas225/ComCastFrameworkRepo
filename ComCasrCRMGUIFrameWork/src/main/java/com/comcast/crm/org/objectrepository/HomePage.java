package com.comcast.crm.org.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Organizations")
	  private WebElement orgTagLink;

	public WebElement getOrgTagLink() {
		return orgTagLink;
	}
	
	@FindBy(linkText="Contacts")
	  private WebElement contactTagLink;
	
	public WebElement getContactTagLink() {
		return contactTagLink;
	}
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
      private WebElement icon;
	
	public WebElement getIcon() {
		return icon;
	}
	
	@FindBy(linkText="Sign Out")
	 private WebElement signOutLink;

	public WebElement getSignoutLink() {
		return signOutLink;
	}
	
	public void signOut() {
		WebDriverUtility webDriverUtility= new WebDriverUtility(driver);
		//hover to Adminstrator icon
		webDriverUtility.mouseHover(icon);
		// Apply Explicit wait for Synchronization
		webDriverUtility.waitForElementPresent(icon, 20);
		//click on Signout link
		signOutLink.click();	
	}
}
