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

	public void initGroupCreation() {
		click(By.name("new"));
	}

	public void fillGroupForm(GroupData group) {
		if (group.name != null) {
			type(By.name("group_name"), group.name);
		}
		if (group.header != null) {
			type(By.name("group_header"), group.header);
		}
		if (group.footer != null) {
			type(By.name("group_footer"), group.footer);
		}
	}

	public void submitGroupCreation() {
		click(By.name("submit"));

	}

	public void returnToGroupsPage() {
		click(By.linkText("group page"));
	}

	public void deleteGroup(int index) {
		selectGroupByIndex(index);
		click(By.name("delete"));
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

	}

	public List<GroupData> getGroups() {
		List<GroupData> groups = new ArrayList<GroupData>();
		List<WebElement> checkboxes = getListWebElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			GroupData group = new GroupData();
			String title = checkbox.getAttribute("title");
			group.name = title.substring("Select (".length(), title.length()
					- ")".length());
			groups.add(group);

		}
		return groups;
	}

	public List<GroupData> transformListGroups(List<GroupData> groups) {
		GroupData noneGroup = new GroupData();
		noneGroup.name = "[none]";
		groups.add(0, noneGroup);
		return groups;
	}

	public GroupData transformGroupToVisibleOnGroupsPage(GroupData group,
			GroupData oldGroup, boolean creation) {
		GroupData newGroup = new GroupData();
		newGroup.name = group.name;
		if (creation) {

			if (newGroup.name == null) {
				newGroup.name = "";

			}

		} else {

			if (newGroup.name == null) {
				newGroup.name = oldGroup.name;
			}

		}

		return newGroup;
	}

}
