package com.goeasyauto.automation.framework.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.sun.tools.sjavac.Log;

public class BrowserSettings {

	private final WebDriver driver;
	private final ElementUtil elementUtil;
	
	public BrowserSettings(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	/*To change the chrome browser setting
	 * 
	 * @param settingTye - type of setting to change like download and location
	 * 
	 * @param applicationUrl - url of the application for which chrome setting is changed 
	 * 
	 * @return true if found and setting changed successfully
	 * 
	 * */
	
	
	public boolean changeChromeSetting(String settingType, String applicationUrl) {
	
		boolean result = true;
		String settingsUrl = null;
		switch(settingType.toLowerCase()) {
		case "download":
			settingsUrl = DataManager.getAutomaticDownloadSettingUrl();
			break;
		case "location":
			settingsUrl = DataManager.getloacationSettingsUrl();
			break;
		case "default":
			log.error("invalid OPtion: {}", settingType);
			result = false;
		}
		
		Log.info("Open Chrome {} Settings", settingsType);
		driver.get(settingsUrl);
		Log.info("Open Chrome {} Settings", settingsType);
		
		WebElement root1 = driver.findElement(By.tagName("setting-ui"));
		WebElement shadowRoot1 = expandRootElement(root1);
		
		WebElement root2 = shadowRoot1.findElement(By.cssSelector("#main"));
		WebElement shadowRoot2 = expandRootElement(root2);
		
		WebElement root3 = shadowRoot2.findElement(By.cssSelector("setting-basic-page.showing-subpage"));
		WebElement shadowRoot3 = expandRootElement(root3);
		
		WebElement root4 = shadowRoot3.findElement(By.cssSelector("setting-privacy-page"));
		WebElement shadowRoot4= expandRootElement(root4);
		
		WebElement root5 = shadowRoot4.findElement(By.cssSelector("category-default-setting"));
		WebElement shadowRoot5= expandRootElement(root5);
		
		WebElement root6 = shadowRoot5.findElement(By.cssSelector("setting-toggle-button"));
		WebElement shadowRoot6= expandRootElement(root6);
		
		WebElement root7 = shadowRoot6.findElement(By.cssSelector("cr-toggle#cotrol"));
		
		elementUtil.click(root7);
	}
	
	
	
	
	
	
	
	
}
