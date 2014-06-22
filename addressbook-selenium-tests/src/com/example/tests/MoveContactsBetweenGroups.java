package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class MoveContactsBetweenGroups extends TestBase {

	@Test
	public void addContactToSomeGroup() {

		app.getNavigationHelper().openMainPage();
		List<ContactData> contactList = app.getContactHelper().getContacts();
		int contactIndex = getRandomIndexOfList(contactList.size());
		ContactData movingContact = contactList.get(contactIndex);
		List<GroupData> groupListTo = app.getContactHelper()
				.getGroupListOfCombobox("addToGroupList");
		List<GroupData> groupListAll = app.getContactHelper()
				.getGroupListOfCombobox("groupListOnMainPage");
		int groupIndexTo = getRandomIndexOfList(groupListTo.size());

		// save old state
		app.getContactHelper().openContactListOfGroup(groupIndexTo + 2);
		List<ContactData> oldList = app.getContactHelper().getContacts();

		// actions
		app.getContactHelper().openContactListOfGroup("[all]");
		List<GroupData> groupListOfContact = app.getContactHelper()
				.getGroupListOfContact(contactIndex);
		app.getNavigationHelper().openMainPage();
		app.getContactHelper().selectContactByIndex(contactIndex);
		app.getContactHelper().addContactsToGroup(groupIndexTo);
		GroupData groupTo = groupListTo.get(groupIndexTo);
		app.getContactHelper().gotoContactListOfGroupByLink(groupTo.name);

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();
		app.getContactHelper().openContactListOfGroup("[all]");

		// compare group lists (if different "compare states" will be invalid)
		GroupData allGroup = new GroupData();
		allGroup.name = "[all]";
		groupListAll.remove(groupListAll.indexOf(allGroup));
		GroupData noneGroup = new GroupData();
		noneGroup.name = "[none]";
		groupListAll.remove(groupListAll.indexOf(noneGroup));
		assertEquals(groupListAll, groupListTo);

		// compare states

		if ((groupTo.name.equals("")) || (groupListOfContact.contains(groupTo))) {

			assertEquals(newList, oldList);

		} else {

			oldList.add(movingContact);
			Collections.sort(oldList);
			assertEquals(newList, oldList);

		}

	}

	@Test
	public void removeAllContactsFromSomeGroup() {
		app.getNavigationHelper().openMainPage();
		List<GroupData> groupList = app.getContactHelper()
				.getGroupListOfCombobox("groupListOnMainPage");
		String groupName;
		int groupIndex;

		do {

			groupIndex = getRandomIndexOfList(groupList.size());
			groupName = groupList.get(groupIndex).name;

		} while (groupName.equals("") | groupName.equals("[all]")
				| groupName.equals("[none]"));

		app.getContactHelper().openContactListOfGroup(groupIndex);
		app.getContactHelper().removeContactsFromGroup();
		app.getContactHelper().gotoContactListOfGroupByLink(groupName);

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		app.getContactHelper().openContactListOfGroup("[all]");

		// compare states
		assertEquals(newList.size(), 0);

	}
}
