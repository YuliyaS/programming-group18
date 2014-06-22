package com.example.tests;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void testContactCreationWithValidData(ContactData contact)
			throws Exception {
		app.getNavigationHelper().openMainPage();

		// save old state
		List<ContactData> oldList = app.getContactHelper().getContacts();

		// actions
		app.getContactHelper().initContactCreation();
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactCreation();
		app.getContactHelper().returnToHomePage();

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		// compare states
		ContactData newContact = new ContactData();
		newContact.id = 999999999;
		newContact.lastname = contact.lastname;
		newContact.firstname = contact.firstname;
		newContact.email1 = contact.email1;
		newContact.email2 = contact.email2;

		if (newContact.lastname == null) {
			newContact.lastname = "";
		}
		if (newContact.firstname == null) {
			newContact.firstname = "";
		}

		if ((newContact.email1 == null) || (newContact.email1.equals(""))) {
			if (newContact.email2 == null) {
				newContact.email2 = "";
			}
			newContact.email1 = newContact.email2;
		}

		oldList.add(newContact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}
