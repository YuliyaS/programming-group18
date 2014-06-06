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
		
		// actions
		app.getContactHelper().initContactModification(index);
		ContactData contact = new ContactData();
		contact.firstname = "new name";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();
		
		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		// compare states
		oldList.remove(index);
		oldList.add(contact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

	@Test
	public void addContactsToSomeGroup() {
		app.getNavigationHelper().openMainPage();
		String fromGroup = "[none]";
		app.getContactHelper().openContactListOfGroup(fromGroup);
		String toGroup = "Rob";
		app.getContactHelper().addContactToGroup(toGroup);
		app.getContactHelper().gotoContactListOfGroupByLink(toGroup);
		String allGroup = "[all]";
		app.getContactHelper().openContactListOfGroup(allGroup);

	}

	@Test
	public void removeAllContactsFromSomeGroup() {
		app.getNavigationHelper().openMainPage();
		String fromGroup = "Rob";
		app.getContactHelper().openContactListOfGroup(fromGroup);
		app.getContactHelper().RemoveContactFromGroup();
		app.getContactHelper().gotoContactListOfGroupByLink(fromGroup);
		String allGroup = "[all]";
		app.getContactHelper().openContactListOfGroup(allGroup);

	}
}
