package com.komodo.NetflixScraper.db.dao;

import java.sql.PreparedStatement;

import com.komodo.NetflixScraper.db.config.DBConecction;

public class ProductGenreDAO {

	public static void save(long idProduct, long idGenre) throws Exception {
		String query = "INSERT INTO ProductGenre " + "(idProduct, idGenre) VALUES (?,?)";
		try (PreparedStatement preparedStatement = DBConecction.getConnection().prepareStatement(query);) {
			preparedStatement.setInt(1, Math.toIntExact(idProduct));
			preparedStatement.setInt(2, Math.toIntExact(idGenre));
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
}
