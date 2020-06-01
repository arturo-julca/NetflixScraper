package com.komodo.NetflixScraper.util;

public class DateUtil {

	public static java.sql.Date getCurrentSqlDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
	
}
