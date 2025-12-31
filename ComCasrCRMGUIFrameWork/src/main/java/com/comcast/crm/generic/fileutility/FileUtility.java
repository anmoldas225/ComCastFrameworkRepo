package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;

import java.util.Properties;

public class FileUtility {
	
	public String getDataFromPropertiesFile(String key) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./src/main/resources/CommonData.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String value = pobj.getProperty(key);
		return value;
	}
}
