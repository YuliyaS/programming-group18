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

public class HelperWithWebDriverBase extends HelperBase {

private WebDriver driver;

public HelperWithWebDriverBase(ApplicationManager manager) {
		super(manager);
		driver = manager.getWebDriverHelper().getDriver();
	}

//	public HelperWithWebDriverBase (ApplicationManager applicationManager) {
		// TODO Auto-generated constructor stub
//	}

	
	public boolean isElementPresent(By by) {
	try {
		findElement(by);
		return true;
	} catch (NoSuchElementException e) {
		return false;
	}
}

protected void type(By locator, String text) {
	if (text != null) {
		findElement(locator).clear();
		findElement(locator).sendKeys(text);
	}
}

protected void click(By locator) {
	findElement(locator).click();
}

protected void selectByText(By locator, String text) {
	if (text != null) {
		new Select(findElement(locator)).selectByVisibleText(text);
	}
}

protected WebElement findElement(By locator) {
	return driver.findElement(locator);
}

protected List<WebElement> findElements(By locator) {
	return driver.findElements(locator);
}

//protected String getCurrentUrl() {
//	return driver.getCurrentUrl();
//}

protected void openUrl(String string) {
	driver.get(manager.getProperty("baseURL") + string);
}


protected void openAbsoluteUrl(String string) {
	driver.get(string);
	
}

}

