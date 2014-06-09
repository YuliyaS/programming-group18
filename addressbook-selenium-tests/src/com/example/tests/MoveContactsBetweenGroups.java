package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class MoveContactsBetweenGroups extends TestBase {

	@Test
	public void addContactsToSomeGroup() {
		app.getNavigationHelper().openMainPage();
		List<GroupData> groupListFrom = app.getContactHelper()
				.getGroupListOfCombobox("groupListOnMainPage");
		Random rndFrom = new Random();
		int groupIndexFrom = rndFrom.nextInt(groupListFrom.size() - 1);
		app.getContactHelper().openContactListOfGroup(groupIndexFrom);
		List<GroupData> groupListTo = app.getContactHelper()
				.getGroupListOfCombobox("addToGroupList");
		Random rndTo = new Random();
		int groupIndexTo = rndTo.nextInt(groupListTo.size() - 1);
		String groupNameTo = groupListTo.get(groupIndexTo).name;
		app.getContactHelper().addContactsToGroup(groupIndexTo);
		app.getContactHelper().gotoContactListOfGroupByLink(groupNameTo);
		app.getContactHelper().openContactListOfGroup(0);

	}

	@Test
	public void removeAllContactsFromSomeGroup() {
		app.getNavigationHelper().openMainPage();
		List<GroupData> groupList = app.getContactHelper()
				.getGroupListOfCombobox("groupListOnMainPage");
		String groupName;
		int groupIndex;
		do {
			Random rnd = new Random();
			groupIndex = rnd.nextInt(groupList.size() - 3) + 2;
			groupName = groupList.get(groupIndex).name;

		} while (groupName == "");
		app.getContactHelper().openContactListOfGroup(groupIndex);
		app.getContactHelper().removeContactsFromGroup();
		app.getContactHelper().gotoContactListOfGroupByLink(groupName);
		app.getContactHelper().openContactListOfGroup(0);

	}
}
