package com.crm.Vtiger.GenericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int count = 0;
	int retryCount = 4;
	
	@Override
	public boolean retry(ITestResult result) {
		while(count <retryCount) {
			count++;
			return true;
		}
		return false;
	}
}


