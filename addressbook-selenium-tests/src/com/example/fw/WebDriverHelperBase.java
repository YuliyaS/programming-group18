package com.example.fw;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class WebDriverHelperBase extends HelperBase {

	protected WebDriver driver;
	public boolean acceptNextAlert = true;

	public WebDriverHelperBase(ApplicationManager manager) {
		super(manager);
		this.driver = manager.getDriver();

	}

	public boolean isElementPresent(By by) {
		try {
			getWebElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	protected void type(By locator, String text) {
		if (text != null) {
			getWebElement(locator).clear();
			getWebElement(locator).sendKeys(text);
		}
	}

	protected void click(By locator) {
		getWebElement(locator).click();
	}

	protected void selectByText(By locator, String text) {
		if (text != null) {
			new Select(getWebElement(locator)).selectByVisibleText(text);
		}
	}

	protected WebElement getWebElement(By locator) {
		return driver.findElement(locator);
	}

	protected List<WebElement> getListWebElements(By locator) {
		return driver.findElements(locator);
	}

	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

}
