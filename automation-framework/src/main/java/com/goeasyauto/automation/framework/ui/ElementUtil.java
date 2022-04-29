package com.goeasyauto.automation.framework.ui;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import org.hamcrest.core.IsNot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

public class ElementUtil {

	private static final Logger log = LogManager.getLogger(ElementUtil.class);
	public long waitTimeInMillis = 60000;
	public int waitTimeInSeconds = 60;
	public long pollingIntervalInMillis = 250;
	public static float devicePixelRatio = 0;
	
	private final WebDriver driver;
	private final Wait<WebDriver> fluentWait;
	private Actions actionBuilder;
	
	public ElementUtil(WebDriver driver) {

	this.driver = driver;
	
	waitTimeInMillis = DataManager.ImplicitWaitInMilliSeconds();
	pollingIntervalInMillis = DataManager.getPollingIntervalInMilliSeconds();
	waitTimeInSeconds = (int) waitTimeInMillis/1000;
	
	fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(waitTimeInMillis))
			.pollingEvery(Duration.ofMillis(pollingIntervalInMillis)).ignoring(NoSuchElementException.class);
	
	devicePixelRatio = getDevicePixelRatio();
		
	}
	
	/*	To check the element is present
	 * */
	public boolean isPresent(WebElement element, Boolean waitflag, Boolean assertFlag) {
		Boolean flag = false;
		if(IsNotNull(element, assertFlag)){
			try {
				element = fluentWait(waitflag).until(ExpectedConditions.visibilityOf(element));
				flag = element != null;
			} catch (Exception e) {
				log.error("Element not found: {}", e.getMessage());
			}
		}
		if(flag)
			log.info("Element found: {}", element);
		else 
			log.info("Element not found: {}", element);
		
		if(assert & !flag)
			Assert.fail("Element not found: " + element);
		
		return flag;
	}
	
	/*To check the element is present
	 * */
	
	public boolean isPresent(WebElement element) {
		return isPresent(element, true, true);
	}
	
	/*To check the element is present
	 * */
	public boolean isPresent(String locator, Boolean waitFlag, Boolean assertFlag) {
		return isPresent(getElement(locator, waitFlag, assertFlag), false, true);
	}
	
	public boolean isPresent(String locator, Boolean waitFlag) {
		return isPresent(getElement(locator, waitFlag, true), false, true);
	}
	
	public boolean isPresent(String locator) {
		return isPresent(getElement(locator, true, true), false, true);
	}
	
	/*To check the element is Displayed
	 * */
	
	public boolean isDisplayed(WebElement element, Boolean waitFlag, Boolean assertFlag) {
		boolean flag = false;
		
		if(isPresent(element, waitFlag, assertFlag)) {
			flag = element.isDisplayed();
		}
		return isDisplayed(flag, true, assertFlag);
		}
	
	public boolean isDisplayed(WebElement element, Boolean waitFlag) {
		return isDisplayed(element, waitFlag,true);
		}
	
	public boolean isDisplayed(WebElement element) {
		return isDisplayed(element, true,true);
		} 
	
	public boolean isDisplayed(String locator, Boolean waitFlag, Boolean assertFlag) {
		return isDisplayed(locator, waitFlag, assertFlag), false, assertFlag);
	}
	
	public boolean isDisplayed(String locator, Boolean waitFlag) {
		return isDisplayed(locator, waitFlag, true);
	}
	
	public boolean isDisplayed(String locator) {
		return isDisplayed(locator, true, true);
	}

	/*To check the element is Not Displayed
	 * */
	
	public boolean isNotDisplayed(WebElement element, Boolean waitFlag, Boolean assertFlag) {
		boolean flag = false;
		if(IsNotNull(element, assertFlag)){
			try {
				element = fluentWait(waitflag).until(ExpectedConditions.visibilityOf(element));
			} catch (Exception e) {
				log.error("Element is displayed: {}", e.getMessage());
			}
		}
		else {
			log.info("Element not found: {}", element);
			flag = true;
		}
		return isDisplayed(flag, false, assertFlag);
	}
	
	public boolean isNotDisplayed(WebElement element, Boolean waitFlag) {
		return isNotDisplayed(element, waitFlag,true);
		}
	
	public boolean isNotDisplayed(WebElement element) {
		return isDisplayed(element, true,true);
		} 
	
	public boolean isNotDisplayed(String locator, Boolean waitFlag, Boolean assertFlag) {
		return isNotDisplayed(getElement(locator, waitFlag, assertFlag)), false, assertFlag);
	}
	
	public boolean isNotDisplayed(String locator, Boolean waitFlag) {
		return isNotDisplayed(locator, waitFlag, true);
	}
	
	public boolean isNotDisplayed(String locator) {
		return isNotDisplayed(locator, true, true);
	}


	/*To check the element is Enabled
	 * */
	
	public boolean isEnabled(WebElement element, Boolean waitFlag, Boolean assertFlag) {
		boolean flag = isNotNull(element, assertFlag);

		try {
			if(flag){
				element = fluentWait(waitflag).until(ExpectedConditions.elementToBeClickable(element));
				flag = element.isEnabled();
		} catch (Exception e) {
			log.error("Element is not anabled; Expection: {}", e.getMessage());
			flag false;
		}
		return isEnabled(flag,true, assertFlag);
		}
	
	public boolean isEnabled(WebElement element, Boolean waitFlag) {
		return isEnabled(element, waitFlag,true);
		}
	
	public boolean isEnabled(WebElement element) {
		return isEnabled(element, true,true);
		} 
	
	public boolean isEnabled(String locator, Boolean waitFlag, Boolean assertFlag) {
		return isEnabled(getElement(locator, waitFlag, assertFlag), false, assertFlag);
	}
	
	public boolean isEnabled(String locator, Boolean waitFlag) {
		return isEnabled(locator, waitFlag, true);
	}
	
	public boolean isEnabled(String locator) {
		return isEnabled(locator, true, true);
	}

	

	/*To check the element is disabled
	 * */
	
	public boolean isDisabled(WebElement element, Boolean waitFlag, Boolean assertFlag) {
		boolean flag = false;

		if(isPresent(element, waitFlag, assertFlag)){
				flag = element.isEnabled() == false;
		} 
		
		return isEnabled(flag,false, assertFlag);
		}
	
	public boolean isDisabled(WebElement element, Boolean waitFlag) {
		return isDisabled(element, waitFlag,true);
		}
	
	public boolean isDisabled(WebElement element) {
		return isDisabled(element, true,true);
		} 
	
	public boolean isDisabled(String locator, Boolean waitFlag, Boolean assertFlag) {
		return isDisabled(getElement(locator, waitFlag, assertFlag), false, assertFlag);
	}
	
	
	/* To check the dropdown element is disabled
	 * 
	 * */
	
	public boolean isDropdownDisabled(WebElement element, Boolean waitFlag, Boolean assertFlag) {
		isPresent(element,waitFlag, assertFlag);
		return element.getAttribute("class").contains("disabled");
	}
	
	public boolean isDropdownDisabled(WebElement element, Boolean waitFlag) {
		return isDropdownDisabled(element, waitFlag, true);
	}
	
	public boolean isDropdownDisabled(WebElement element) {
		return isDropdownDisabled(element, true, true);
	}

	public boolean isDropdownDisabled(String locator, Boolean waitFlag, Boolean assertFlag) {
		return isDropdownDisabled(getElement(locator, waitFlag, assertFlag), false, assertFlag);
	}
	
	public boolean isDropdownDisabled(String locator, Boolean waitFlag) {
		return isDropdownDisabled(getElement(locator, waitFlag, true), false, true);
	}
	
	public boolean isDropdownDisabled(String locator) {
		return isDropdownDisabled(getElement(locator, true, true), false, true);
	}
	
	
	/*TO click the element*/
	
	// Line 503 start
	
}
