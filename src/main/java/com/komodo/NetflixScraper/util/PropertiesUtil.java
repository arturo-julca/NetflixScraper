package com.komodo.NetflixScraper.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	static private Properties prop;

	public static String get(String propertyName) {
		if (prop == null) {
			getPropValues();
		}
		return prop.getProperty(propertyName);
	}

	private static void getPropValues() {
		prop = new Properties();
		String propertiesFileName = "application.properties";
		
		String path = PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
		System.out.println("path:"+path);
				
		String totalPath = "/"+path + propertiesFileName;
		System.out.println("total path:"+totalPath);
		try (InputStream applicationsInputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(totalPath);) {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}

}
