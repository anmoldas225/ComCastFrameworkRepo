package com.comcast.crm.generic.listenerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImplemen implements IRetryAnalyzer{

	int count = 0, limitcount = 5;
	@Override
	public boolean retry(ITestResult result) {
		if(count<limitcount) {
			count++;
			return true;
		}
		return false;
	}
	

}
