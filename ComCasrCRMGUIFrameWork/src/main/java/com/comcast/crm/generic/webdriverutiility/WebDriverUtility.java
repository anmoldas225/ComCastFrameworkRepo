package com.comcast.crm.generic.webdriverutiility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
    
	WebDriver driver;
	Actions act;
	WebDriverWait wait;
	
	public WebDriverUtility(WebDriver driver){		
		this.driver = driver;
		this.act = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public void maximize() {
		
		driver.manage().window().maximize();
	}
	
	public void waitforpagetoload(long second) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(second));
	}
	
	public void get(String url){
		
		driver.get(url);
	}

	public void waitForElementPresent(WebElement element, long second) {

		WebDriverWait driverwait = new WebDriverWait(driver, Duration.ofSeconds(second));
		driverwait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void close() {
		
		driver.close();
	}
	
    public void quit() {
		
		driver.quit();
	}

	public void switchtowindowonURL(String partialurl) {

		Set<String> set1 = driver.getWindowHandles();

		Iterator<String> it1 = set1.iterator();

		while (it1.hasNext()) {
			String wid = it1.next();
			driver.switchTo().window(wid);
			String acturl = driver.getCurrentUrl();

			if (acturl.contains(partialurl)) {
				break;
			}
		}

	}
	public void switchtowindowonTitle(String partialTitle) {
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1 = set1.iterator();
		while (it1.hasNext()) {
			String wid = it1.next();
			driver.switchTo().window(wid);
			String actTitle = driver.getTitle();

			if (actTitle.contains(partialTitle)) {
				break;
			}
		}

	}

	public void switchtoFrame(int index) {

		driver.switchTo().frame(index);
	}

	public void switchtoFrame(String NameID) {

		driver.switchTo().frame(NameID);
	}

	public void switchtoFrame(WebElement element) {

		driver.switchTo().frame(element);
	}

	public void switchtoAlertAndgetText() {

		driver.switchTo().alert().getText();
	}

	public void switchtoAlertAndSendKeys(String value) {

		driver.switchTo().alert().sendKeys(value);
	}
	
	public void switchtoAlertAndAceept() {

		driver.switchTo().alert().accept();
	}

	public void switchtoAlertAndCancel() {

		driver.switchTo().alert().dismiss();
	}
	
	public void selectbyVisibleText(WebElement element,String text) {
		
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
    public void selectbyIndex(WebElement element, int index) {
		
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
    
    public void selectbyValue(WebElement element, String value) {
		
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
    
    public void mouseHover(WebElement element) {
    	   
    	    act.moveToElement(element).perform();
    }
    
    public void doubleClick(WebElement element) {
 	   
	    act.doubleClick(element).perform();
    }
    
    public List<String> getOptions(WebElement webElement){
    	  
    	   Select sel = new Select(webElement);
    	   List<WebElement> options = sel.getOptions();  
    	   List<String> optiontexts = new ArrayList<>();
    	   
    	   for(WebElement option:options) {  
    		optiontexts.add(option.getText().trim());
    	   }
    	 return optiontexts;
    }

}
