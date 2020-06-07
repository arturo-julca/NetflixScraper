package com.komodo.NetflixScraper.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.komodo.NetflixScraper.db.config.DBConecction;
import com.komodo.NetflixScraper.db.model.Genre;

public class GenreDAO {

	public static Genre save(String genreName) {
		String query = "INSERT INTO Genre " + "(name) VALUES (?)";
		try (PreparedStatement preparedStatement = DBConecction.getConnection().prepareStatement(query);) {
			preparedStatement.setString(1, genreName);
			preparedStatement.executeUpdate();
			return getFromName(genreName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Genre parseResult(ResultSet rs) throws SQLException{
		Genre genreModel = new Genre();
		genreModel.setIdGenre(rs.getInt("id_genre"));
		genreModel.setName(rs.getString("name"));
		return genreModel;
	}
	
	public static Genre getFromName(String genreName) {
		Genre genreModel = null;
		String query = "SELECT * FROM Genre WHERE name = ?";		
		try (PreparedStatement preparedStatement = DBConecction.getConnection().prepareStatement(query);) {
			preparedStatement.setString(1, genreName);			
			ResultSet rs = preparedStatement.executeQuery();			
			while (rs.next()) {
				genreModel = parseResult(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genreModel;
	}
}
