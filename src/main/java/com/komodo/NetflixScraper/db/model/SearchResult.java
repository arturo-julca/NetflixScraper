package com.komodo.NetflixScraper.db.model;

import java.util.Date;

public class SearchResult {

	private long id;
	private String foundName;
	private boolean online;
	private Date firstFoundedAt;
	private Date lastFoundedAt;
	private long idProduct;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFoundName() {
		return foundName;
	}
	public void setFoundName(String foundName) {
		this.foundName = foundName;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public Date getFirstFoundedAt() {
		return firstFoundedAt;
	}
	public void setFirstFoundedAt(Date firstFoundedAt) {
		this.firstFoundedAt = firstFoundedAt;
	}
	public Date getLastFoundedAt() {
		return lastFoundedAt;
	}
	public void setLastFoundedAt(Date lastFoundedAt) {
		this.lastFoundedAt = lastFoundedAt;
	}
	public long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}
	
	@Override
	public String toString() {
		return "SearchResult [id=" + id + ", foundName=" + foundName + ", online=" + online + ", firstFoundedAt="
				+ firstFoundedAt + ", lastFoundedAt=" + lastFoundedAt + ", idProduct=" + idProduct + "]";
	}

	
}
