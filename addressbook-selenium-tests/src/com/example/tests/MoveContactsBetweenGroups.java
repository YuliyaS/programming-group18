package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

import static com.example.tests.RandomDataGenerator.getRandomIndexOfList;

import static com.example.tests.RandomDataGenerator.getRandomIndexOfGroupWithNonEmptyGroupName;

public class MoveContactsBetweenGroups extends TestBase {

	@Test
	public void addContactToSomeGroup() {

		// get test data
		SortedListOf<ContactData> allContacts = app.getContactHelper()
				.getAllContacts();
		SortedListOf<GroupData> groups = app.getGroupHelper().getGroups();
		int contactIndex = getRandomIndexOfList(allContacts.size());
		int groupIndex = getRandomIndexOfList(groups.size());
		ContactData contact = allContacts.get(contactIndex);
		GroupData group = groups.get(groupIndex);

		// save old state
		SortedListOf<ContactData> oldList = app.getContactHelper()
				.getContactsInGroup(groupIndex);
		SortedListOf<GroupData> groupsOfContact = app.getContactHelper()
				.getGroupListOfContact(contactIndex);
		boolean contactAlreadyInGroup = (group.getName().equals(""))
				|| (groupsOfContact.contains(group));

		// actions
		app.getContactHelper().addSomeContactToGroup(contactIndex, groupIndex,
				group.getName());

		// save new state
		SortedListOf<ContactData> newList = app.getContactHelper()
				.getContactsInGroup(groupIndex);

		// compare states
		if (contactAlreadyInGroup) {
			assertThat(newList, equalTo(oldList));
		} else {
			assertThat(newList, equalTo(oldList.withAdded(contact)));
		}
	}

	@Test
	public void removeAllContactsFromSomeGroup() {

		// get test data
		int index = getRandomIndexOfGroupWithNonEmptyGroupName();
		String name = app.getGroupHelper().getGroups().get(index).getName();

		// actions
		app.getContactHelper().removeAllContactsFromGroup(name);

		// save new state
		SortedListOf<ContactData> newList = app.getContactHelper()
				.getContactsInGroup(index);

		// compare states
		assertThat(newList.size(), equalTo(0));
	}
}
