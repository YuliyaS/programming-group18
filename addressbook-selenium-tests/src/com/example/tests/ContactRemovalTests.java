package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactRemovalTests extends TestBase {

	@Test
	public void deleteSomeContact() {

		// save old state
		SortedListOf<ContactData> oldList = app.getContactHelper().getAllContacts();
		
		// actions
		int index = getRandomIndexOfList(oldList.size());
		app.getContactHelper().deleteContact(index);

		// save new state
		SortedListOf<ContactData> newList = app.getContactHelper().getAllContacts();

		// compare states
		assertThat(newList, equalTo(oldList.without(index)));
	}

}