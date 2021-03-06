package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;
import com.example.utils.SortedListOf;

public class GroupHelper extends WebDriverHelperBase {

	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	public GroupHelper createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		initGroupCreation();
		fillGroupForm(group);
		submitGroupCreation();
		returnToGroupsPage();
		manager.getModel().addGroup(
				transformGroupToVisibleOnGroupsPage(group, group, CREATION));
		return this;
	}

	public GroupHelper modifyGroup(GroupData group, GroupData oldGroup,
			int index) {
		manager.navigateTo().groupsPage();
		initGroupModification(index);
		fillGroupForm(group);
		submitGroupModification();
		returnToGroupsPage();
		manager.getModel()
				.removeGroup(index)
				.addGroup(
						transformGroupToVisibleOnGroupsPage(group, oldGroup,
								MODIFICATION));
		return this;
	}

	public GroupHelper deleteGroup(int index) {
		manager.navigateTo().groupsPage();
		selectGroupByIndex(index);
		submitGroupDeletion();
		returnToGroupsPage();
		manager.getModel().removeGroup(index);
		return this;
	}

	// -----------------------------------------------------------------------------------

	public SortedListOf<GroupData> getUiGroups() {
		SortedListOf<GroupData> groups = new SortedListOf<GroupData>();
		manager.navigateTo().groupsPage();
		List<WebElement> checkboxes = getListWebElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			String title = checkbox.getAttribute("title");
			String name = title.substring("Select (".length(), title.length()
					- ")".length());
			groups.add(new GroupData().withName(name));
		}
		return groups;
	}

	public GroupHelper initGroupCreation() {
		click(By.name("new"));
		return this;
	}

	public GroupHelper fillGroupForm(GroupData group) {
		if (group.getName() != null) {
			type(By.name("group_name"), group.getName());
		}
		if (group.getHeader() != null) {
			type(By.name("group_header"), group.getHeader());
		}
		if (group.getFooter() != null) {
			type(By.name("group_footer"), group.getFooter());
		}
		return this;
	}

	public GroupHelper submitGroupCreation() {
		click(By.name("submit"));
		return this;
	}

	public GroupHelper returnToGroupsPage() {
		click(By.linkText("group page"));
		return this;
	}

	private GroupHelper selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]'][" + (index + 1) + "]"));
		return this;
	}

	public GroupHelper initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));
		return this;
	}

	public GroupHelper submitGroupModification() {
		click(By.name("update"));
		return this;
	}

	public GroupData transformGroupToVisibleOnGroupsPage(GroupData group,
			GroupData oldGroup, boolean formType) {

		String name = group.getName();
		if (formType == CREATION) {

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

	private GroupHelper submitGroupDeletion() {
		click(By.name("delete"));
		return this;
	}

}
