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
		List<GroupData> groupList = app.getContactHelper().getGroupListOfCombobox("listOnMainPage");
		Random rnd = new Random();
		int groupIndex = rnd.nextInt(groupList.size() - 1);
		String fromGroup = "[none]";
		app.getContactHelper().openContactListOfGroup(fromGroup);
		String toGroup = groupList.get(groupIndex).name;
		app.getContactHelper().addContactToGroup(toGroup);
		app.getContactHelper().gotoContactListOfGroupByLink(toGroup);
		String allGroup = "[all]";
		app.getContactHelper().openContactListOfGroup(allGroup);

	}

	//@Test
	public void removeAllContactsFromSomeGroup() {
		app.getNavigationHelper().openMainPage();
		String fromGroup = "авпавп";
		app.getContactHelper().openContactListOfGroup(fromGroup);
		app.getContactHelper().RemoveContactFromGroup();
		app.getContactHelper().gotoContactListOfGroupByLink(fromGroup);
		String allGroup = "[all]";
		app.getContactHelper().openContactListOfGroup(allGroup);

	}
}
