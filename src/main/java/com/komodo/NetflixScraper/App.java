package com.komodo.NetflixScraper;


public class App {
	public static void main(String[] args) {
		System.out.println("Hello Crawler!");
//		Crawler crawler = new Crawler();
//		crawler.crawlNetflix();
		OMDBJob omdbjob = new OMDBJob();
		omdbjob.fillOMDBInfo();
	}
}
