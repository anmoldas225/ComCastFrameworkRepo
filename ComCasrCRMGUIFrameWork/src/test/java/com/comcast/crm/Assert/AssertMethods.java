package com.comcast.crm.Assert;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseutility.BaseClass;

public class AssertMethods extends BaseClass{
	
	@Test
	public void assertMethod(Method mtd){
		
		System.out.println(mtd.getName() + "Verification");
		Reporter.log("Step-1");     //It will Print only in HTML Report.
		System.out.println("Step-1");
		Assert.assertEquals("Home", "Home");
		System.out.println("Step-2");
		Assert.assertNotEquals("home", "home");
		System.out.println("Step-3");

		System.out.println(mtd.getName() + "Verification Done");
        
	}
	
	@Test
	public void assertMethods(Method mtd){
		
		System.out.println(mtd.getName() + "Verification");
		System.out.println("Step-1");
		Assert.assertTrue(true);
		System.out.println("Step-2");
		Assert.assertFalse(true);
		System.out.println("Step-3");

		System.out.println(mtd.getName() + "Verification Done");

	}
	
}
