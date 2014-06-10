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
		List<ContactData> contactList = app.getContactHelper().getContacts();
		Random rnd = new Random();
		int contactIndex = rnd.nextInt(contactList.size() - 1);
		ContactData movingContact = contactList.get(contactIndex);

		List<GroupData> groupListTo = app.getContactHelper()
				.getGroupListOfCombobox("addToGroupList");
		List<GroupData> groupListAll = app.getContactHelper()
				.getGroupListOfCombobox("groupListOnMainPage");
		Random rndTo = new Random();
		int groupIndexTo = rndTo.nextInt(groupListTo.size() - 1);

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
		
		if (groupListOfContact.contains(groupTo)) {

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
		GroupData group = new GroupData();
		int groupIndex;
		
		do {
			
			Random rnd = new Random();
			groupIndex = rnd.nextInt(groupList.size() - 1);
			group = groupList.get(groupIndex);
			groupName = group.name;
			
		} while (groupName.equals("")|groupName.equals("[all]")|groupName.equals("[none]"));
		
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
