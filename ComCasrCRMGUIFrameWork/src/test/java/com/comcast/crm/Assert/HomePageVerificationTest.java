package com.comcast.crm.Assert;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePageVerificationTest {
	
	@Test
	public void homePageTest(Method mtd) {
		
		System.out.println(mtd.getName() + " Test Start");
		String expectedPage = "Home";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8888/");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
     String actualTitle=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		
//       if(actualTitle.trim().equals(expectedPage))
//       {
//    	     System.out.println(expectedPage + "Page is Verified=Pass");
//       }else {
//    	     System.out.println(expectedPage + "Page is not Verified=Fail");    
//       }
//		System.out.println(mtd.getName() + " Test End");
     
        //Hard Assert and have static Methods
       
        Assert.assertEquals(actualTitle, expectedPage); 
		System.out.println(mtd.getName() + " Test End");
        driver.quit();
        
	}
	
	
	@Test
	public void verifyLogoHomePageTest(Method mtd) {
		
		System.out.println(mtd.getName() + " Test Start");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8888/");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
        driver.findElement(By.id("submitButton")).click();
        
      boolean status=  driver.findElement(By.xpath("//img[@alt='vtiger-crm-logo.gif']")).isEnabled();
	   
       Assert.assertTrue(status); 
	   System.out.println(mtd.getName() + " Test End");
	   driver.quit();
	}
	

}
