package com.comcast.crm.org.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	WebDriver driver;
	
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//click on add or + contact module button. 
		@FindBy(xpath="//img[@alt ='Create Contact...']")
		private WebElement createContactBtn;
		
		public WebElement getCreateContactBtn() {
			return createContactBtn;
		}
	
	

}
