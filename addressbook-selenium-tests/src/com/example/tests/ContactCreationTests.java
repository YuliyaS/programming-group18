package com.example.tests;

import org.testng.annotations.Test;

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
		contact.group = "Rob";
		contact.address2 = "address2";
		contact.home_phone2 = "home_phone2";
		app.getNavigationHelper().openMainPage();
		app.getContactHelper().initContactCreation();
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactCreation();
		app.getContactHelper().returnToHomePage();
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
		app.getContactHelper().initContactCreation();
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactCreation();
		app.getContactHelper().returnToHomePage();
	}

}
