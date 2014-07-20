package com.example.tests;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

import static com.example.fw.ContactHelper.CREATION;
import static com.example.tests.ContactDataGenerator.loadContactsFromCsvFile;
import static com.example.tests.ContactDataGenerator.loadContactsFromXmlFile;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ContactCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> contactsFromFile() throws IOException {
		String fileName = getProperties().getProperty("contactsDataFile");
		return wrapContactsForDataProvider(
				loadContactsFromCsvFile(new File(fileName))).iterator();
	}

	@Test(dataProvider = "contactsFromFile")
	public void testContactCreationWithValidData(ContactData contact)
			throws Exception {

		// save old state
		SortedListOf<ContactData> oldList = app.getModel().getContacts();

		// actions
		app.getContactHelper().createContact(contact);

		// save new state
		SortedListOf<ContactData> newList = app.getModel().getContacts();
		ContactData newContact = app.getContactHelper()
				.transformContactToVisibleOnContactsPage(contact, contact,
						CREATION);

		// compare states
		assertThat(newList, equalTo(oldList.withAdded(newContact)));
		assertThat(app.getModel().getContacts(), equalTo(app
				.getHibernateHelper().listContacts()));
		assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper()
				.getUiContacts()));

	}

}
