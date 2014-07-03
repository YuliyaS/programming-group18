package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class MoveContactsBetweenGroups extends TestBase {

	@Test
	public void addContactToSomeGroup() {

		List<ContactData> contactList = app.getContactHelper().getContacts();
		int contactIndex = getRandomIndexOfList(contactList.size());
		ContactData movingContact = contactList.get(contactIndex);
		List<GroupData> groupListTo = app.getGroupHelper().getGroups();
		int groupIndexTo = getRandomIndexOfList(groupListTo.size());
		GroupData groupTo = groupListTo.get(groupIndexTo);

		// save old state
		app.getContactHelper().openContactListOfGroup(groupIndexTo + 2);
		List<ContactData> oldList = app.getContactHelper().getContacts();
		app.getContactHelper().openContactListOfGroup("[all]");
		List<GroupData> groupListOfContact = app.getContactHelper()
				.getGroupListOfContact(contactIndex);

		// actions
		app.getContactHelper().addSomeContactToGroup(contactIndex,
				groupIndexTo, groupTo.getName());

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();
		app.getContactHelper().openContactListOfGroup("[all]");

		// compare states
		if ((groupTo.getName().equals(""))
				|| (groupListOfContact.contains(groupTo))) {

			assertEquals(newList, oldList);

		} else {

			oldList.add(movingContact);
			Collections.sort(oldList);
			assertEquals(newList, oldList);
		}
	}

	@Test
	public void removeAllContactsFromSomeGroup() {

		// actions
		String name = getRandomNonEmptyGroupName();
		app.getContactHelper().removeAllContactsFromGroup(name);

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		app.getContactHelper().openContactListOfGroup("[all]");

		// compare states
		assertEquals(newList.size(), 0);

	}
}

