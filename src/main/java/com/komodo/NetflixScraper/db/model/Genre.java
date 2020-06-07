package com.komodo.NetflixScraper.db.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "Genre")
public class Genre {

	@Column(name = "id_genre")
	private long idGenre;
	private String name;
	
	public Genre(){		
	}
	
	public Genre(String name){
		this.name = name;
	}
	

	public long getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(long idGenre) {
		this.idGenre = idGenre;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Genre [idGenre=" + idGenre + ", name=" + name + "]";
	}	
}