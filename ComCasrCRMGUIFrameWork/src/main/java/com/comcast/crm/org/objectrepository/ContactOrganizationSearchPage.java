package com.comcast.crm.org.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;

public class ContactOrganizationSearchPage {
	
	WebDriver driver;
	WebDriverUtility webDriverUtility;

	public ContactOrganizationSearchPage(WebDriver driver) {
	this.driver=driver;
	webDriverUtility=new WebDriverUtility(driver);
	PageFactory.initElements(driver, this);
	}
	
	//send detail to search textfield.
    @FindBy(name="search_text")
    private WebElement searchText; 
    
    public WebElement getSearchText() {
		return searchText;
	}

    //hit search now button.
    @FindBy(name="search")
	private WebElement searchBtn; 
    
	public WebElement getSearchBtn() {
		return searchBtn;
	}

}
