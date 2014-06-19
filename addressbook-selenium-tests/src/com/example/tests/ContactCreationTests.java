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
		if (contact.lastname == null) {
			contact.lastname = "";
		}
		if (contact.firstname == null) {
			contact.firstname = "";
		}
		// if (contact.email1 == null) {contact.email1="";}
		if ((contact.email1 == null) || (contact.email1.isEmpty())) {
			if (contact.email2 == null) {
				contact.email2 = "";
			}
			contact.email1 = contact.email2;
		}
		oldList.add(contact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}
