package com.komodo.NetflixScraper.db.model;

public class ProductGenre {

	private long idProduct;
	private long idGenre;
	
	public ProductGenre(long idProduct, long idGenre){
		this.idProduct = idProduct;
		this.idGenre = idGenre;
	}
	
	public long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}
	public long getIdGenre() {
		return idGenre;
	}
	public void setIdGenre(long idGenre) {
		this.idGenre = idGenre;
	}
}
