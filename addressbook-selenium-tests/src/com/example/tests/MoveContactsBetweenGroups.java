package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class MoveContactsBetweenGroups extends TestBase {

	@Test
	public void addContactToSomeGroup() {

		app.navigateTo().mainPage();
		List<ContactData> contactList = app.getContactHelper().getContacts();
		int contactIndex = getRandomIndexOfList(contactList.size());
		ContactData movingContact = contactList.get(contactIndex);
		app.navigateTo().groupsPage();
		List<GroupData> groupListTo = app.getGroupHelper().getGroups();
		int groupIndexTo = getRandomIndexOfList(groupListTo.size());

		// save old state
		app.navigateTo().mainPage();
		app.getContactHelper().openContactListOfGroup(groupIndexTo + 2);
		List<ContactData> oldList = app.getContactHelper().getContacts();

		// actions
		app.getContactHelper().openContactListOfGroup("[all]");
		List<GroupData> groupListOfContact = app.getContactHelper()
				.getGroupListOfContact(contactIndex);
		app.navigateTo().mainPage();
		app.getContactHelper().selectContactByIndex(contactIndex);
		app.getContactHelper().addContactsToGroup(groupIndexTo);
		GroupData groupTo = groupListTo.get(groupIndexTo);
		app.getContactHelper().gotoContactListOfGroupByLink(groupTo.getName());

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();
		app.getContactHelper().openContactListOfGroup("[all]");

		// compare states
		if ((groupTo.getName().equals("")) || (groupListOfContact.contains(groupTo))) {

			assertEquals(newList, oldList);

		} else {

			oldList.add(movingContact);
			Collections.sort(oldList);
			assertEquals(newList, oldList);

		}

	}

	@Test
	public void removeAllContactsFromSomeGroup() {
		app.navigateTo().mainPage();
		List<GroupData> groupList = app.getContactHelper()
				.getGroupListOfCombobox("groupListOnMainPage");
		String groupName;
		int groupIndex;

		do {

			groupIndex = getRandomIndexOfList(groupList.size());
			groupName = groupList.get(groupIndex).getName();

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
