package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends WebDriverHelperBase {

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}

	public void mainPage() {
		if (!onMainPage()) {
			click(By.linkText("home"));

		}
	}

	public void groupsPage() {
		if (!onGroupsPage()) {
			click(By.linkText("groups"));
		}

	}

	private boolean onMainPage() {
		return getListWebElements(By.id("maintable")).size() > 0;
	}

	private boolean onGroupsPage() {
		return (getCurrentUrl().contains("/group.php") && getListWebElements(
				By.name("new")).size() > 0);

	}

}
