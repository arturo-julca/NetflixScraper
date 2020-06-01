package com.komodo.NetflixScraper.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.komodo.NetflixScraper.db.model.Product;

public class RequestUtil {

	private static String OMDB_URL = "http://www.omdbapi.com/";
	private static String OMDB_KEY = "?apikey="+PropertiesUtil.get("omdb.apikey")+"&t=";

	public static Product sentRequest(String movieName) throws Exception {
		String finalURL = OMDB_URL + OMDB_KEY + urlFormat(movieName);
		System.out.println(finalURL);
		URL url = new URL(finalURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		
		System.out.println(content);
		return parseJson(content.toString(), movieName);
	}
	
	private static String urlFormat(String movieTitle){
		return movieTitle.replace(" ", "+");
	}
	
	private static Product parseJson(String response, String movieName){		
		JsonParser parser = new JsonParser();
		JsonObject jsonRoot = parser.parse(response).getAsJsonObject();
		Product productModel = new Product();
		if(jsonRoot.get("Error")!=null){
			productModel.setTitle(movieName);
			return productModel;
		}
		productModel.setTitle(jsonRoot.get("Title").getAsString());
		productModel.setYear(jsonRoot.get("Year").getAsString());
		productModel.setRuntime(jsonRoot.get("Runtime").getAsString());
		productModel.setDirector(jsonRoot.get("Director").getAsString());
		productModel.setImdbRating(parseScore(jsonRoot, "imdbRating"));
		productModel.setMetascore(parseScore(jsonRoot, "Metascore"));
		productModel.setListGenres(parseGenres(jsonRoot));
		
		return productModel;
	}
	
	private static List<String> parseGenres(JsonObject jsonObject){
		String genresString = jsonObject.get("Genre").getAsString();
		List<String> listGenres = Arrays.asList(genresString.split(", "));
		return listGenres;
	}
	
	private static Double parseScore(JsonObject jsonObject, String fieldname){
		String score = jsonObject.get(fieldname).getAsString();
		if(score.equalsIgnoreCase("N/A")){
			score = "0";
		}
		return Double.parseDouble(score);
	}
}
