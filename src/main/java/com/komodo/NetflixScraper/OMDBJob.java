package com.komodo.NetflixScraper;

import com.komodo.NetflixScraper.db.dao.GenreDAO;
import com.komodo.NetflixScraper.db.dao.ProductDAO;
import com.komodo.NetflixScraper.db.dao.ProductGenreDAO;
import com.komodo.NetflixScraper.db.dao.SearchResultDAO;
import com.komodo.NetflixScraper.db.model.Genre;
import com.komodo.NetflixScraper.db.model.Product;
import com.komodo.NetflixScraper.db.model.SearchResult;
import com.komodo.NetflixScraper.util.RequestUtil;

public class OMDBJob {

	static public void main(String args[]) {
		OMDBJob job = new OMDBJob();
		job.fillOMDBInfo();
	}

	public void fillOMDBInfo() {
		try {
			for (SearchResult searchResult : SearchResultDAO.getAllToSearch()) {
				Product product = RequestUtil.sentRequest(searchResult.getFoundName());
				ProductDAO.create(product);
				product.setId(ProductDAO.getIdFromTitle(product.getTitle()));
				searchResult.setIdProduct(product.getId());
				SearchResultDAO.updateSearchResult(searchResult);
				saveGenres(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveGenres(Product product) throws Exception {
		if(product.getListGenres()==null){
			return;
		}
		for (String genreName : product.getListGenres()) {
			Genre genreModel = GenreDAO.getFromName(genreName);
			// if its a new genre, it saves it
				if (genreModel == null) {
				genreModel = GenreDAO.save(genreName);
			}
			// saves ProductGenre
			ProductGenreDAO.save(product.getId(), genreModel.getIdGenre());
		}
	}
}
