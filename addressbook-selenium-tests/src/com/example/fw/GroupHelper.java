package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;

public class GroupHelper extends HelperBase {

	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	private List<GroupData> cachedGroups;

	public List<GroupData> getGroups() {
		if (cachedGroups == null) {
			rebuildCache();
		}
		return cachedGroups;

	}

	private void rebuildCache() {
		cachedGroups = new ArrayList<GroupData>();
		List<WebElement> checkboxes = getListWebElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			String title = checkbox.getAttribute("title");
			String name = title.substring("Select (".length(), title.length()
					- ")".length());
			cachedGroups.add(new GroupData().withName(name));

		}

	}

	public void initGroupCreation() {
		click(By.name("new"));
	}

	public void fillGroupForm(GroupData group) {
		if (group.getName() != null) {
			type(By.name("group_name"), group.getName());
		}
		if (group.getHeader() != null) {
			type(By.name("group_header"), group.getHeader());
		}
		if (group.getFooter() != null) {
			type(By.name("group_footer"), group.getFooter());
		}
	}

	public void submitGroupCreation() {
		click(By.name("submit"));
		cachedGroups = null;

	}

	public void returnToGroupsPage() {
		click(By.linkText("group page"));
	}

	public void deleteGroup(int index) {
		selectGroupByIndex(index);
		click(By.name("delete"));
		cachedGroups = null;
	}

	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]'][" + (index + 1) + "]"));
	}

	public void initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));
	}

	public void submitGroupModification() {
		click(By.name("update"));
		cachedGroups = null;

	}

	public GroupData transformGroupToVisibleOnGroupsPage(GroupData group,
			GroupData oldGroup, boolean creation) {

		String name = group.getName();
		if (creation) {

			if (name == null) {
				name = "";

			}

		} else {

			if (name == null) {
				name = oldGroup.getName();
			}

		}

		return new GroupData().withName(name);
	}

}
