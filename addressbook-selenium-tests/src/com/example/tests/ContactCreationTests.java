package com.example.tests;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

	@Test
	public void testNonEmptyContactCreation() throws Exception {
		ContactData contact = new ContactData();
		contact.firstname = "firstname";
		contact.lastname = "lastname";
		contact.address1 = "address1";
		contact.home_phone1 = "home_phone1";
		contact.mobile_phone = "mobile_phone";
		contact.work_phone = "work_phone";
		contact.email1 = "email1";
		contact.email2 = "email2";
		contact.bday = "19";
		contact.bmonth = "November";
		contact.byear = "1980";
		contact.address2 = "address2";
		contact.home_phone2 = "home_phone2";
		app.getNavigationHelper().openMainPage();

		// save old state
		List<ContactData> oldList = app.getContactHelper().getContacts();

		// actions
		app.getContactHelper().initContactCreation();

		List<GroupData> groupList = app.getContactHelper()
				.getGroupListOfCombobox("groupListOnAddContactPage");
		Random rnd = new Random();
		int groupIndex = rnd.nextInt(groupList.size() - 1);
		contact.group = groupList.get(groupIndex).name;

		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactCreation();
		app.getContactHelper().returnToHomePage();

		// save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		// compare states
		oldList.add(contact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

	@Test
	public void testEmptyContactCreation() throws Exception {
		ContactData contact = new ContactData();
		contact.firstname = "";
		contact.lastname = "";
		contact.address1 = "";
		contact.home_phone1 = "";
		contact.mobile_phone = "";
		contact.work_phone = "";
		contact.email1 = "";
		contact.email2 = "";
		contact.bday = "-";
		contact.bmonth = "-";
		contact.byear = "";
		contact.group = "";
		contact.address2 = "";
		contact.home_phone2 = "";
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
		oldList.add(contact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);
	}

}
