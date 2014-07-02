package com.example.tests;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import static com.example.fw.ContactHelper.CREATION;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void testContactCreationWithValidData(ContactData contact)
			throws Exception {
		app.navigateTo().mainPage();

		// save old state
		List<ContactData> oldList = app.getContactHelper().getContacts();

		// actions
		app.getContactHelper().initContactCreation();
		app.getContactHelper().fillContactForm(contact, CREATION);
		app.getContactHelper().submitContactCreation();
		app.getContactHelper().returnToHomePage();

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();
		ContactData newContact = app.getContactHelper().transformContactToVisibleOnContactsPage(contact,
				contact, CREATION);

		// compare states
		oldList.add(newContact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}
