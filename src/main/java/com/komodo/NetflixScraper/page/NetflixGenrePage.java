package com.komodo.NetflixScraper.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class NetflixGenrePage {

	private WebDriver driver;
	
	public NetflixGenrePage(WebDriver driver, String pageUrl){
		this.driver = driver;
		driver.get(pageUrl);
	}
	
	public List<String> getMovieNames() throws InterruptedException{
		clickGridDisplayIcon();
		Thread.sleep(3000);
		loadAllContent();
		Thread.sleep(1000);
		return getMovieTitlesFromThumbnails(getGenereTile());
	}
	
	private void clickGridDisplayIcon(){
		WebElement gridDisplayIcon = driver.findElement(By.cssSelector(".aro-grid-toggle"));
		gridDisplayIcon.click();
	}
	
	private String getGenereTile(){
		WebElement genereTitle = driver.findElement(By.cssSelector(".genreTitle"));
		return genereTitle.getText();
	}
	
	private int getNumberOfMovies(){
		List<WebElement> listMovieThumbnails = driver.findElements(By.cssSelector(".boxart-image.boxart-image-in-padded-container"));
		int numberOfMovies = listMovieThumbnails.size();
		return numberOfMovies;
	}
	
	private void loadAllContent() throws InterruptedException{
		int initialNumberOfMovies = getNumberOfMovies();
		int currentNumberOfMovies = 0;
		boolean stillDataToLoad = true;
		while(stillDataToLoad){
			scrollToBottomPage();
			currentNumberOfMovies = getNumberOfMovies();
			if(currentNumberOfMovies == initialNumberOfMovies){
				stillDataToLoad = false;
				break;
			}else{
				initialNumberOfMovies = currentNumberOfMovies;
			}
		}
		System.out.println("number movies:"+currentNumberOfMovies);
		scrollToTopPage();
	}
	
	private void scrollToBottomPage() throws InterruptedException{
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
	}
	
	private void scrollToTopPage() throws InterruptedException{
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(2000);
	}
	
	private List<String> getMovieTitlesFromThumbnails(String genereTitle) throws InterruptedException{
		List<String> listMovieTitles = new ArrayList<String>();
		List<WebElement> listMovieLinks = driver.findElements(By.cssSelector("a.slider-refocus"));
		for(WebElement movieLink : listMovieLinks) {
			String title = movieLink.getAttribute("aria-label");
			System.out.println("title:"+title);
	    	listMovieTitles.add(title);
	    	break;
	    }
	    return listMovieTitles;
	}
}
