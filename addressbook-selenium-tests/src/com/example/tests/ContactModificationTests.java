package com.example.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

	@Test
	public void modifySomeContact() {
		app.getNavigationHelper().openMainPage();
		app.getContactHelper().initContactModification(1);
		ContactData contact = new ContactData();
		contact.firstname = "new name";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();

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
