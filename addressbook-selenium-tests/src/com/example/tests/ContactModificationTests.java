package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import static com.example.fw.ContactHelper.MODIFICATION;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void modifySomeContact(ContactData contact) throws Exception {
		app.getNavigationHelper().openMainPage();

		// save old state
		List<ContactData> oldList = app.getContactHelper().getContacts();
		int index = getRandomIndexOfList(oldList.size());
		app.getContactHelper().initContactModification(index);
		ContactData oldContact = app.getContactHelper()
				.getContactDataOnEditingForm();

		// actions
		app.getContactHelper().fillContactForm(contact, MODIFICATION);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		// compare states
		oldList.remove(index);
	//	ContactData newContact = new ContactData();
	//	newContact.id = oldContact.id;
	//	newContact.lastname = contact.lastname;
	//	newContact.firstname = contact.firstname;
	//	newContact.email1 = contact.email1;
	//	newContact.email2 = contact.email2;

	//	if (newContact.lastname == null) {
	//		newContact.lastname = oldContact.lastname;
	//	}

	//	if (newContact.firstname == null) {
	//		newContact.firstname = oldContact.firstname;
	//	}

	//	if (newContact.email1 == null) {
		//	newContact.email1 = oldContact.email1;
	//	}

	//	if (newContact.email1.equals("")) {
		//	if (newContact.email2 == null) {
		//		newContact.email2 = oldContact.email2;
		//	}
		//	newContact.email1 = newContact.email2;
	//	}
		ContactData newContact = app.getContactHelper().getContactDataVisibleOnContactsPage(contact, oldContact, MODIFICATION);
		oldList.add(index, newContact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}
