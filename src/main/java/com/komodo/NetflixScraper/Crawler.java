package com.komodo.NetflixScraper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.komodo.NetflixScraper.db.dao.SearchResultDAO;
import com.komodo.NetflixScraper.page.NetflixGenrePage;
import com.komodo.NetflixScraper.page.NetflixHomePage;
import com.komodo.NetflixScraper.page.NetflixLoginPage;

public class Crawler {

	private WebDriver driver;
	private NetflixLoginPage netflixLoginPage;
	private NetflixHomePage netflixHomePage;
	
	public Crawler() {
		System.setProperty("webdriver.chrome.driver", "/Users/arturo/dev/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
		netflixLoginPage =  new NetflixLoginPage(driver);
		netflixHomePage =  new NetflixHomePage(driver);
	}

	public void crawlNetflix() {
		try {
			Thread.sleep(1000);
			netflixLoginPage.login();
			netflixLoginPage.selectUser();
			netflixHomePage.selectSection("Movies");
			int counter = 1;
			for(String genereURL : netflixHomePage.getListGenereUrls()){
				if(counter>=2){
					break;
				}
				counter++;
				saveListMovies(new NetflixGenrePage(driver, genereURL).getMovieNames()); 
			}
			driver.quit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void saveListMovies(List<String> listMovies){
		for(String movieTitle : listMovies){
			SearchResultDAO.save(movieTitle);
		}
	}
}
