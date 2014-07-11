package com.example.tests;

import org.testng.annotations.Test;

import com.example.fw.Contact;

public class TestContactCreation extends TestBase {
	
	@Test
	public void shoudCreateContactWithValidData() {
		
		Contact contact = new Contact().setFirstName("tester_firstname").setLastName("tester_lastname");
		app.getContactHelper().createContact(contact);
		
	}

}
