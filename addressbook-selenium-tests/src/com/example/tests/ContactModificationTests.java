package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

	@Test
	public void modifySomeContact() {
		app.getNavigationHelper().openMainPage();

		// save old state
		List<ContactData> oldList = app.getContactHelper().getContacts();

		Random rnd = new Random();
		int index = rnd.nextInt(oldList.size() - 1);
		ContactData newContact = oldList.get(index);

		// actions
		app.getContactHelper().initContactModification(index);
		ContactData modifiedContact = new ContactData();
		modifiedContact.firstname = "new name";
		app.getContactHelper().fillContactForm(modifiedContact);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		// compare states
		oldList.remove(index);
		newContact.firstname = modifiedContact.firstname;
		oldList.add(newContact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}
