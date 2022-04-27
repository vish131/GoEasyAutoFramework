package com.goeasyauto.automation.framework.desktoapp;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.goeasyauto.automation.framework.ui.DriverType;
import com.mongodb.diagnostics.logging.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	/*
	 * This is the helper class for initializing Driver for windows application
	 * */
	
	private WebDriver driver;
	private static final Logger log = LogManager.getLogger(DriverManager.class);
	
	/*
	 * //@param driverType - Type of driver
	//@param applicationPath - Path of the application
	 * 
	 * */
	
	public WebDriver getDriverforElectronApp(DriverType driverType, String applicationPath) {
		try {
			switch(driverType) {
			case FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case CHROME:	
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				 if(!(applicationPath == null || applicationPath.isEmpty())) {
					 options.setBinary(applicationPath);
				 }
				 driver = new ChromeDriver(options);
				 break;
			case IE:
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			case EDGE:
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			case HEADLESS:
				WebDriverManager.chromedriver().setup();
				ChromeOptions option = new ChromeOptions();
				option.addArguments("headless");
				driver = new ChromeDriver(option);
				break;
			default:
				log.error("No driver found for the given driver type : {}",driverType);
				break;
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(DataManager.getImplicitWitInMilliSecod()));
		}
		catch (Exception e) {
			log.error("driver is not initialized for the driver type: {};  Exception", driverType, e.getMessage());
		}
	
	return driver;
}
	/* This method will close the application
	 * 
	 * */
	public void closeApp() {
		driver.close();
	}
	
	/*
	 * This method will close the application
	 * 
	 * */
	public void quitApp() {
		driver.quit();
	}
	
}
