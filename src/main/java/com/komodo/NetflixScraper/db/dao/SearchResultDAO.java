package com.komodo.NetflixScraper.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.komodo.NetflixScraper.db.config.DBConecction;
import com.komodo.NetflixScraper.db.model.SearchResult;
import com.komodo.NetflixScraper.util.DateUtil;

public class SearchResultDAO {

	private SearchResultDAO() {
	}

	public static void save(String foundName) {
		if(getFromName(foundName)!=null){
			return;
		}
		createSearchResult(foundName);	
	}

	public static SearchResult getFromName(String foundName) {
		SearchResult searchResultModel = null;
		String query = "SELECT * FROM SearchResult WHERE foundName = ?";		
		try (PreparedStatement preparedStatement = DBConecction.getConnection().prepareStatement(query);) {
			preparedStatement.setString(1, foundName);			
			ResultSet rs = preparedStatement.executeQuery();			
			while (rs.next()) {
				searchResultModel = parseResult(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultModel;
	}
	
	private static SearchResult parseResult(ResultSet rs) throws SQLException{
		SearchResult searchResultModel = new SearchResult();
		searchResultModel.setId(rs.getInt("id"));
		searchResultModel.setFoundName(rs.getString("foundName"));
		searchResultModel.setOnline(((rs.getInt("online")==1)? true:false));
		searchResultModel.setFirstFoundedAt(rs.getDate("firstFoundedAt"));
		searchResultModel.setLastFoundedAt(rs.getDate("lastFoundedAt"));
		searchResultModel.setIdProduct(rs.getInt("idProduct"));
		return searchResultModel;
	}
	
	public static List<SearchResult> getAllToSearch() {
		List<SearchResult> searchResultModelList = new ArrayList<SearchResult>();
		String query = "SELECT * FROM SearchResult WHERE idProduct IS NULL";
		try (PreparedStatement preparedStatement = DBConecction.getConnection().prepareStatement(query);) {
			ResultSet rs = preparedStatement.executeQuery();			
			while (rs.next()) {
				searchResultModelList.add(parseResult(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultModelList;
	}

	private static void createSearchResult(String foundName) {
		String query = "INSERT INTO SearchResult" + "(foundName, online, firstFoundedAt) VALUES (?,?,?)";

		try (PreparedStatement preparedStatement = DBConecction.getConnection().prepareStatement(query);) {
			preparedStatement.setString(1, foundName);
			preparedStatement.setInt(2, 1);
			preparedStatement.setDate(3, DateUtil.getCurrentSqlDate());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateSearchResult(SearchResult searchResultModel) {
		String query = "UPDATE SearchResult SET idProduct = ? WHERE id = ?";

		try (PreparedStatement preparedStatement = DBConecction.getConnection().prepareStatement(query);) {
			preparedStatement.setInt(1, Math.toIntExact(searchResultModel.getIdProduct()));
			preparedStatement.setInt(2, Math.toIntExact(searchResultModel.getId()));
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
