package com.comcast.crm.generic.webdriverutiility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	SimpleDateFormat sdf;
	String sysdate;
	
	public int getRandomNumber() {
		
		Random random = new Random();
		int rnum = random.nextInt(5000);
		return rnum;
	}
	
	public int generateRandomNum() {
		double random = Math.random() * 9999;
		int ranNum = (int)random;
		return ranNum;
	}
	
	public String getSystemDateYYYYMMDD(){
		
		Date dateobj = new Date();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		sysdate = sdf.format(dateobj);
		
		return sysdate;
	}
	
	// required date is always greater than SystemDate 
	public String getRequiredDateYYYYMMDD(int days){
	    Calendar cal = sdf.getCalendar();
	    cal.add(Calendar.DAY_OF_MONTH, days);
        String requiredate = sdf.format(cal.getTime());	
		return requiredate;
	}
	
	
}
