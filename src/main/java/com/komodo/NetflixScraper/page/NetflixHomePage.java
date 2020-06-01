package com.komodo.NetflixScraper.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NetflixHomePage {
	
	private WebDriver driver;
	
	public NetflixHomePage(WebDriver driver){
		this.driver = driver;
	}

	public void selectSection(String sectionName) throws InterruptedException {
		List<WebElement> listUserProfiles = driver.findElements(By.cssSelector(".navigation-tab>a"));
		for (WebElement section : listUserProfiles) {
			if (section.getText().equalsIgnoreCase(sectionName)) {
				section.click();
			}
		}
		Thread.sleep(5000);
	}

	public void clickGenresDropbox() throws InterruptedException {
		List<WebElement> listLabels = driver.findElements(By.cssSelector(".label"));
		for (WebElement label : listLabels) {
			if (label.getText().equalsIgnoreCase("Genres")) {
				label.click();
				break;
			}
		}
		Thread.sleep(2000);
	}

	public List<String> getListGenereUrls() throws InterruptedException {
		clickGenresDropbox();
		List<WebElement> listGenereOptions = driver.findElements(By.cssSelector(".sub-menu-link"));
		List<String> listUrls = new ArrayList<String>();
		for (WebElement genereOption : listGenereOptions) {
			listUrls.add(genereOption.getAttribute("href"));
		}

		return listUrls;
	}
}
