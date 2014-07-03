package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class MoveContactsBetweenGroups extends TestBase {

	@Test
	public void addContactToSomeGroup() {

		SortedListOf<ContactData> contactList = app.getContactHelper()
				.getContacts();
		int contactIndex = getRandomIndexOfList(contactList.size());
		ContactData movingContact = contactList.get(contactIndex);
		SortedListOf<GroupData> groupListTo = app.getGroupHelper().getGroups();
		int groupIndexTo = getRandomIndexOfList(groupListTo.size());
		GroupData groupTo = groupListTo.get(groupIndexTo);

		// save old state
		app.getContactHelper().openContactListOfGroup(groupIndexTo + 2);
		SortedListOf<ContactData> oldList = app.getContactHelper()
				.getContacts();
		app.getContactHelper().openContactListOfGroup("[all]");
		SortedListOf<GroupData> groupListOfContact = app.getContactHelper()
				.getGroupListOfContact(contactIndex);
		boolean contactAlreadyInGroup = (groupTo.getName().equals(""))
				|| (groupListOfContact.contains(groupTo));

		// actions
		app.getContactHelper().addSomeContactToGroup(contactIndex,
				groupIndexTo, groupTo.getName());

		// save new state
		SortedListOf<ContactData> newList = app.getContactHelper()
				.getContacts();
		app.getContactHelper().openContactListOfGroup("[all]");

		// compare states
		if (contactAlreadyInGroup) {
          assertThat(newList, equalTo(oldList));
		} else {
		assertThat(newList, equalTo(oldList.withAdded(movingContact)));
	}}

	@Test
	public void removeAllContactsFromSomeGroup() {

		// actions
		String name = getRandomNonEmptyGroupName();
		app.getContactHelper().removeAllContactsFromGroup(name);

		// save new state
		SortedListOf<ContactData> newList = app.getContactHelper()
				.getContacts();

		app.getContactHelper().openContactListOfGroup("[all]");

		// compare states
		assertThat(newList.size(), equalTo(0));

	}
}
