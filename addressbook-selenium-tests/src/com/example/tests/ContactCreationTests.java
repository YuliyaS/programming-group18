package com.example.tests;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

import static com.example.fw.ContactHelper.CREATION;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ContactCreationTests extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void testContactCreationWithValidData(ContactData contact)
			throws Exception {

		// save old state
		SortedListOf<ContactData> oldList = app.getContactHelper()
				.getAllContacts();

		// actions
		app.getContactHelper().createContact(contact);

		// save new state
		SortedListOf<ContactData> newList = app.getContactHelper()
				.getAllContacts();
		ContactData newContact = app.getContactHelper()
				.transformContactToVisibleOnContactsPage(contact, contact,
						CREATION);

		// compare states
		assertThat(newList, equalTo(oldList.withAdded(newContact)));

	}

}
