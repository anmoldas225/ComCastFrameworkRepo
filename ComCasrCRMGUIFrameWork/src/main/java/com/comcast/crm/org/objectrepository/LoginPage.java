package com.comcast.crm.org.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutiility.WebDriverUtility;

public class LoginPage { //Rule1: create a separate java class
	                     //Rule2: Object Creation  
	FileUtility fileUtility;
	WebDriverUtility webDriverUtility;
	public LoginPage(WebDriver driver) {
		fileUtility= new FileUtility();
		webDriverUtility=new WebDriverUtility(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement userName;
	
	public WebElement getUserName() {
		return userName;
	}
	
	@FindBy(name="user_password")
	private WebElement passWord;
	
	public WebElement getPassword() {
		return passWord;
	}
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule3:Object Initialization
    //Rule4:object Encapsulation.

	public void login() throws Throwable {
		String useName=fileUtility.getDataFromPropertiesFile("un");
		String passWor=fileUtility.getDataFromPropertiesFile("pwd");
	       userName.sendKeys(useName);
	       passWord.sendKeys(passWor);
	       loginBtn.click();
	}
	
    public void login(long secondforImplicitwait, String url,String username,String password) throws Throwable {
    	       webDriverUtility.maximize();
    	       webDriverUtility.waitforpagetoload(secondforImplicitwait);
    	       webDriverUtility.get(url);
	       userName.sendKeys(username);
	       passWord.sendKeys(password);
	       loginBtn.click();
	}
	                   
}
