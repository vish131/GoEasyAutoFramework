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
		
		if(settingType.equalsIgnoreCase("download")) {
			WebElement root8 = shadowRoot4.findElement(By.cssSelector("category-setting-exceptions"));
			WebElement shadowRoot8 = expandRootElement(root8);
			WebElement root9 = shadowRoot8.findElement("site-list[category-header='Allow']");
			WebElement shadowRoot9 = expandRootElement(root9);
			WebElement root10 = shadowRoot9.findElement(By.cssSelector("cr-button#addsite"));
			
			elementUtil.click(root10);
			Log.info("clicked Add Site in Download Allow");
			
			WebElement root11 = shadowRoot9.findElement(By.cssSelector("add-site-dialog"));
			WebElement shadowRoot11 = expandRootElement(root11);
			WebElement root12 = shadowRoot11.findElement(By.cssSelector("cr-input#site"));
			WebElement shadowRoot12 = expandRootElement(root12);
			WebElement root13 = shadowRoot12.findElement(By.cssSelector("input#input"));
			
			elementUtil.enterText(root13, applicationUrl);
			Log.info("Entered {} in Download Allow TextBox", applicationUrl);
			
			WebElement root14 = shadowRoot11.findElement(By.cssSelector("cr-button#add"));
			
			elementUtil.click(root14);
			Log.info("CLicked Add Button");
			
		}
		
		return result;
				
	}
	
	
	/*
	 * To expand the shadow root element in chrome setting page
	 * 
	 * Element - Webelement to expand
	 * 
	 * return by locator of element
	 * */
	
	private WebElement expapndRootElement(WebElement element) {
		WebElement ele = (WebElement) elementUtil.jsExecuteScriptWithReturn("return arguments[0].shadowRoot",element);
		Log.info("Element: {}", ele);
		return ele;
	}	
	
}
