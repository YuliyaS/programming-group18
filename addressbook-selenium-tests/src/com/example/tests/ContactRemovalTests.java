package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase {

	@Test
	public void deleteSomeContact() {

		// save old state
		List<ContactData> oldList = app.getContactHelper().getContacts();

		// actions
		int index = getRandomIndexOfList(oldList.size());
		app.getContactHelper().deleteContact(index);

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		// compare states
		oldList.remove(index);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}