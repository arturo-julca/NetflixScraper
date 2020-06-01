package com.komodo.NetflixScraper.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.komodo.NetflixScraper.util.PropertiesUtil;

public class NetflixLoginPage {

	private WebDriver driver;

	public NetflixLoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void login() {
		driver.get(PropertiesUtil.get("netflix.url.login"));
		WebElement userInput = driver.findElement(By.id("id_userLoginId"));
		userInput.sendKeys(PropertiesUtil.get("netflix.user"));
		WebElement passwordInput = driver.findElement(By.id("id_password"));
		passwordInput.sendKeys(PropertiesUtil.get("netflix.pass"));
		passwordInput.submit();		
	}
	
	public void selectUser(){
		List<WebElement> listUserProfiles = driver.findElements(By.cssSelector(".profile-icon"));
		listUserProfiles.get(0).click();
	}
}
