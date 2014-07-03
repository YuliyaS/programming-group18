package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import static com.example.fw.ContactHelper.MODIFICATION;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void modifySomeContact(ContactData contact) throws Exception {

		// save old state
		List<ContactData> oldList = app.getContactHelper().getContacts();
		int index = getRandomIndexOfList(oldList.size());
		ContactData oldContact = app.getContactHelper()
				.getContactDataOnEditingForm(index);

		// actions
		app.getContactHelper().modifyContact(index, contact);

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();
		ContactData newContact = app.getContactHelper()
				.transformContactToVisibleOnContactsPage(contact, oldContact,
						MODIFICATION);

		// compare states
		oldList.remove(index);
		oldList.add(index, newContact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}
