package com.comcast.crm.Listener;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseutility.BaseClass;


//@Listeners(com.comcast.crm.generic.listenerutility.ListenerImpClass.class)
public class InvoiceTest extends BaseClass{
	
	@Test
	public void createInvoiceTest() {
		
		System.out.println("Step 1");
		System.out.println("Step 2");
		String var = driver.getTitle();
		Assert.assertEquals(var, "Login");
		System.out.println("Step 3");
		System.out.println("Step 4");
		
	}
	
	@Test
	public void createInvoiceWithContactTest() {
		
		System.out.println("Step 1");
		System.out.println("Step 2");
		String var = driver.getTitle();
		Assert.assertEquals(var, "Login");
		System.out.println("Step 3");
		System.out.println("Step 4");
		
	}
	

	
}
