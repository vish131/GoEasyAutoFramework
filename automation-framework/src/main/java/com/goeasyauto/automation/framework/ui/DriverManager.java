package com.goeasyauto.automation.framework.ui;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import com.aventstack.extentreports.reporter.FileUtil;
import com.goeasyauto.automation.framework.dataprovider.DriverManager;
import com.goeasyauto.automation.framework.db.DriverType;
import com.paulhammant.ngwebdriver.NgWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {


	//This is he helper class for initializing Drivers for window application
	private WebDriver driver;
	private NgWebDriver ngWebDriver; 
	private static final Logger log = LogManager.getLogger(DriverManager.class);
	
	//@param driverType - Type of driver
	//@param downloadDir - directory for downloads
	//@return Webdriver based on type of the driver
	
	public WebDriver getDRiverforElectronApp(DriverType driverType, String downloadDir) {
		try {
			switch(driverType) {
			case FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case CHROME:	
				WebDriverManager.chromedriver().setup();
				 if(!(downloadDir == null || downloadDir.isEmpty())) {
					 driver = new ChromeDriver();
				 }
				 else {
					 FileUtil.createDirectory(downloadDir);
					 String downloadPath = FileUtil.getAbsolutePath(downloadDir);
					 ChromeOptions = new ChromeOptions();
					 HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					 chromePrefs.put("profile.default_contentsettings.popups", 0);
					 chromePrefs.put("download.default_directory", downloadPath);
					 options.setExperimentalOption("prefs",chromePrefs);
					 LoggingPreference logPrefs = new LoggingPreferences();
					 // To get network logs
					 logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
					 // To get console logs
					 logPrefs.enable(LogType.BROWSER, Level.ALL);
					 // To get driver logs
					 logPrefs.enable(LogType.DRIVER, Level.ALL);
					 options.setCapability("goog:loggingPrefs", logPrefs );
				 	 driver = new ChromeDriver(options);
				 }
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
			maximizeWindow();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(DataManager.getImplicitWitInMilliSecod()));
		}
		catch (Exception e) {
			log.error("driver is not initialized for the driver type: {};  Exception: {}", driverType, e.getMessage());
		}
		ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
	return driver;
}

	// This method will wait for all angular request to finish
	
	public void waitForAngular() {
		log.info("Waiting for all the angular request to finish");
		ngWebDriver.waitForAngularRequestsToFinish();
	}
	
	// This method will return the NgWebDriver
	//@return NgWebDriver
	
	public NgWebDriver getNgWebDriver() {
		return ngWebDriver;
	}
	
	
	//This method will return the current URL in the browser
	//@param baseurl - url which needs to loaded in broswer
	
	public void naigateToUrl(String baseUrl) {
		driver.get(baseUrl);
	}
	
	public String getCurrentPageTITle() {
		return driver.getTitle();
	}
	
	public void closeBrowser() {
		driver.close();
	}
	
	public Set<String> getAllWindowHandles() {
		return driver.getWindowHandles();
	}
	
	public void closeAllBrowserWindows() {
		set<> windowHAndles = getAllWindowHandles();
		for (String window : WindowHandles) {
			driver.switchTo().window(window);
			driver.close();
		}
	}
	
	public void quitBrowser() {
		driver.quit();
	}
	
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	
	public void switchToTab(int tabNo) {
		ArrayList<String> tabs = new ArrayList<String>(getAllWindowHandles());
		driver.switchTo().window(tabs.get(tabNo));
	}
	
	public void makeWindowVisible() {
		driver.switchTo().window(driver.getWindowHandle());
	}
	
	//This method will kill the driver for the respective browser
	
	public static void killDriver(DriverType driverType) {
		String driverExe = null;
		switch (driverType) {
		case FIREFOX:
			driverExe = "geckodriver.exe";
			break;
		case CHROME:
		case HEADLESS:
			driverExe = "chromedriver.exe";
			break;
		case IE:
			driverExe = "IEDriverServer.exe";
			break;
		case EDGE:	
			driverExe = "MicrosoftWebDriver.exe";
			break;
		default:
			log.error("taskkill /F /IM " + driverType);
			break;
		}
		String command = "taskkill /F /IM " + driverExe;
			
		try {
				Runtime.getRuntime().exec(command);
				} catch (IOException e) {
	log.error("Unable to kill the driver: {}; EXpected{}", driverExe, e.getMessage());
				}

		}
	}