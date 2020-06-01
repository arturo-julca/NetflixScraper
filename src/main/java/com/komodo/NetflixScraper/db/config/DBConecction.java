package com.komodo.NetflixScraper.db.config;

import java.sql.Connection;
import java.sql.DriverManager;

import com.komodo.NetflixScraper.util.PropertiesUtil;

public class DBConecction {

	private static Connection conn;
	
	private DBConecction(){
	}
	
	private static Connection createConnection() {
		try {
			Class.forName(PropertiesUtil.get("db.driver"));
			conn = DriverManager.getConnection(
					PropertiesUtil.get("db.url"), 
					PropertiesUtil.get("db.user"), 
					PropertiesUtil.get("db.pass"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConnection (){
		if(conn==null){
			createConnection();
		}
		return conn;
	}
}