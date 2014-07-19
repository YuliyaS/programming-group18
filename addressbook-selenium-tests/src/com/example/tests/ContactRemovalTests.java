package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

import static com.example.tests.RandomDataGenerator.getRandomIndexOfList;

public class ContactRemovalTests extends TestBase {

	@Test
	public void deleteSomeContact() {

		// save old state
		SortedListOf<ContactData> oldList = app.getModel().getContacts();

		// actions
		int index = getRandomIndexOfList(oldList.size());
		app.getContactHelper().deleteContact(index);

		// save new state
		SortedListOf<ContactData> newList = app.getModel().getContacts();

		// compare states
		assertThat(newList, equalTo(oldList.without(index)));
		assertThat(app.getModel().getContacts(), equalTo(app
				.getHibernateHelper().listContacts()));

	}

}