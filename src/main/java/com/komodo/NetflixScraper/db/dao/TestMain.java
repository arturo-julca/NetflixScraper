package com.komodo.NetflixScraper.db.dao;

import com.komodo.NetflixScraper.db.model.Product;
import com.komodo.NetflixScraper.util.RequestUtil;

public class TestMain {

	public static void main(String args[]){
		try{
			
		
//		ProductModel model = new ProductModel("la rana", "test");
//		ProductModel model2 = new ProductModel("la pata", "wow");
//		ProductModel model3 = new ProductModel("la paasdasta", "afasdas");
//		
//		ProductModelDAO.createProduct(model);
//		ProductModelDAO.createProduct(model2);
//		ProductModelDAO.createProduct(model3);
		
//		System.out.println(PropertiesUtil.get("db.user"));
//		System.out.println(PropertiesUtil.get("db.pass"));
//		System.out.println(PropertiesUtil.get("db.url"));
		
//		SearchResultDAO.save("TEST");
//		SearchResultDAO.save("TEST2");

		Product prod = RequestUtil.sentRequest("la la land");
		System.out.println(prod);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
