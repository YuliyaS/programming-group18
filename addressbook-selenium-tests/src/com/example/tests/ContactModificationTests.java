package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static com.example.fw.ContactHelper.MODIFICATION;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactModificationTests extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void modifySomeContact(ContactData contact) throws Exception {

		// save old state
		SortedListOf<ContactData> oldList = app.getContactHelper()
				.getAllContacts();
		int index = getRandomIndexOfList(oldList.size());
		ContactData oldContact = app.getContactHelper()
				.getContactDataOnEditingForm(index);

		// actions
		app.getContactHelper().modifyContact(index, contact);

		// save new state
		SortedListOf<ContactData> newList = app.getContactHelper()
				.getAllContacts();
		ContactData newContact = app.getContactHelper()
				.transformContactToVisibleOnContactsPage(contact, oldContact,
						MODIFICATION);

		// compare states
		assertThat(newList, equalTo(oldList.without(index)
				.withAdded(newContact)));
	}
}
