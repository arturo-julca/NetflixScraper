package com.komodo.NetflixScraper.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.komodo.NetflixScraper.db.config.DBConecction;
import com.komodo.NetflixScraper.db.model.Product;

public class ProductDAO {

	private ProductDAO() {
	}
	
	public static void create(Product productModel) throws SQLException {
		
		String insertTableSQL = "INSERT INTO Product" + "(title, year, runtime, director, imdb_rating, metascore) VALUES (?,?,?,?,?,?)";
		
		PreparedStatement preparedStatement = DBConecction.getConnection().prepareStatement(insertTableSQL);

		preparedStatement.setString(1, productModel.getTitle());
		preparedStatement.setString(2, productModel.getYear());
		preparedStatement.setString(3, productModel.getRuntime());
		preparedStatement.setString(4, productModel.getDirector());
		preparedStatement.setDouble(5, productModel.getImdbRating());
		preparedStatement.setDouble(6, productModel.getMetascore());
		preparedStatement.executeUpdate();
	}
	
	public static int getIdFromTitle(String title) throws SQLException {
		int id = 0;
		String insertTableSQL = "SELECT id_product FROM Product WHERE title = ?";
		PreparedStatement preparedStatement = DBConecction.getConnection().prepareStatement(insertTableSQL);
		preparedStatement.setString(1, title);
		preparedStatement.executeQuery();
		
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			id = rs.getInt("id_product");
		}
		
		return id;
	}
}
