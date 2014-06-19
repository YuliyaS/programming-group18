package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void modifySomeContact(ContactData newContact) throws Exception {
		newContact.group = null;
		app.getNavigationHelper().openMainPage();

		// save old state
		List<ContactData> oldList = app.getContactHelper().getContacts();
		int index = getRandomIndexOfList(oldList.size());
		ContactData oldContact = app.getContactHelper()
				.getContactByIndex(index);

		// actions
		app.getContactHelper().initContactModification(index);
		app.getContactHelper().fillContactForm(newContact);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		// compare states
		oldList.remove(index);
		// newContact.firstname = modifiedContact.firstname;

		if (newContact.lastname == null) {
			newContact.lastname = oldContact.lastname;
		}
		if (newContact.firstname == null) {
			newContact.firstname = oldContact.firstname;
		}
		if (newContact.email1 == null) {
			newContact.email1 = oldContact.email1;
		}
		if (newContact.email1.isEmpty()) {
			if (newContact.email2 == null) {
				newContact.email2 = oldContact.email2;
			}
			newContact.email1 = newContact.email2;
		}
		oldList.add(index, newContact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}
