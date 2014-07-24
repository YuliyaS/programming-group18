package com.example.fw;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class WebDriverHelper {

	protected WebDriver driver;
	public boolean acceptNextAlert = true;
	private Properties properties;
	private String baseUrl;
	private String implicitlyWait;
	private final ApplicationManager manager;

	public WebDriverHelper(ApplicationManager manager) {
		this.manager = manager;
		this.driver = getDriver();

	}

//	public boolean isElementPresent(By by) {
//		try {
//			getWebElement(by);
//			return true;
//		} catch (NoSuchElementException e) {
//			return false;
//		}
//	}
//
//	public boolean isAlertPresent() {
//		try {
//			driver.switchTo().alert();
//			return true;
//		} catch (NoAlertPresentException e) {
//			return false;
//		}
//	}
//
//	public String closeAlertAndGetItsText() {
//		try {
//			Alert alert = driver.switchTo().alert();
//			String alertText = alert.getText();
//			if (acceptNextAlert) {
//				alert.accept();
//			} else {
//				alert.dismiss();
//			}
//			return alertText;
//		} finally {
//			acceptNextAlert = true;
//		}
//	}
//
//	protected void type(By locator, String text) {
//		if (text != null) {
//			getWebElement(locator).clear();
//			getWebElement(locator).sendKeys(text);
//		}
//	}
//
//	protected void click(By locator) {
//		getWebElement(locator).click();
//	}
//
//	protected void selectByText(By locator, String text) {
//		if (text != null) {
//			new Select(getWebElement(locator)).selectByVisibleText(text);
//		}
//	}
//
//	protected WebElement getWebElement(By locator) {
//		return driver.findElement(locator);
//	}
//
//	protected List<WebElement> getListWebElements(By locator) {
//		return driver.findElements(locator);
//	}
//
//	protected String getCurrentUrl() {
//		return driver.getCurrentUrl();
//	}
	

	public WebDriver getDriver() {
		String browser = properties.getProperty("browser");
		if (driver == null) {
			if ("firefox".equals(browser)) {
				driver = new FirefoxDriver();
			} else if ("ie".equals(browser)) {
				driver = new InternetExplorerDriver();
			} else {
				throw new Error("Unsupported browser: " + browser);
			}
			baseUrl = properties.getProperty("baseUrl");
			implicitlyWait = properties.getProperty("implicitlyWait");
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(implicitlyWait), TimeUnit.SECONDS);
			driver.get(baseUrl);
		}
		return driver;
		
	}

}
