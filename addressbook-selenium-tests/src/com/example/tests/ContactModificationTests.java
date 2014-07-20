package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static com.example.fw.ContactHelper.MODIFICATION;
import org.testng.annotations.Test;
import static com.example.tests.RandomDataGenerator.getRandomIndexOfList;

import com.example.utils.SortedListOf;

public class ContactModificationTests extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void modifySomeContact(ContactData contact) throws Exception {

		// save old state
		SortedListOf<ContactData> oldList = app.getModel().getContacts();
		int index = getRandomIndexOfList(oldList.size());
		ContactData oldContact = app.getModel().getContact(index);

		// actions
		app.getContactHelper().modifyContact(index, contact, oldContact);

		// save new state
		SortedListOf<ContactData> newList = app.getModel().getContacts();
		ContactData newContact = app.getContactHelper()
				.transformContactToVisibleOnContactsPage(contact, oldContact,
						MODIFICATION);

		// compare states
		assertThat(newList, equalTo(oldList.without(index)
				.withAdded(newContact)));
		assertThat(app.getModel().getContacts(), equalTo(app
				.getHibernateHelper().listContacts()));
		assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper()
				.getUiContacts()));

	}
}
