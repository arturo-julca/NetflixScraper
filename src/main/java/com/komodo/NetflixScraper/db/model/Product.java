package com.komodo.NetflixScraper.db.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Product {

	@Column(name = "id_product")
	private long id;
	private String title;
	private String year;
	private String runtime;
	private String director;
	private double metascore;
	
	@Column(name = "imdb_rating")
	private double imdbRating;
	private List<String> listGenres;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public double getMetascore() {
		return metascore;
	}
	public void setMetascore(double metascore) {
		this.metascore = metascore;
	}
	public double getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}
	public List<String> getListGenres() {
		return listGenres;
	}
	
	public void setListGenres(List<String> listGenres) {
		this.listGenres = listGenres;
	}
	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", title=" + title + ", year=" + year + ", runtime=" + runtime + ", director="
				+ director + ", metascore=" + metascore + ", imdbRating=" + imdbRating + "]";
	}
}
