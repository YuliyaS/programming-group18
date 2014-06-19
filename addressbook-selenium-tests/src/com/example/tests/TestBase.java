package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

public class TestBase {

	protected static ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager();

	}

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();

	}

	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < 5; i++) {
			GroupData group = new GroupData();
			group.name = generateRandomString();
			group.header = generateRandomString();
			group.footer = generateRandomString();
			list.add(new Object[] { group });
		}
		return list.iterator();
	}

	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		app.getNavigationHelper().openMainPage();
		app.getContactHelper().initContactCreation();
		for (int i = 0; i < 5; i++) {

			ContactData contact = new ContactData();

			contact.firstname = generateRandomString();
			contact.lastname = generateRandomString();
			contact.address1 = generateRandomString();
			contact.home_phone1 = generateRandomString();
			contact.mobile_phone = generateRandomString();
			contact.work_phone = generateRandomString();
			contact.email1 = generateRandomString();
			contact.email2 = generateRandomString();
			contact.byear = generateRandomYear();
			contact.address2 = generateRandomString();
			contact.home_phone2 = generateRandomString();

			contact.bday = getRandomListElement("bday");
			contact.bmonth = getRandomListElement("bmonth");
			contact.group = getRandomGroup("groupListOnAddContactPage").name;

			list.add(new Object[] { contact });
		}
		return list.iterator();
	}

	public int getRandomIndexOfList(int listsize) {
		Random rnd = new Random();
		int index;
		if (rnd.nextInt(3) == 0) {
			index = 0;
		} else {
			index = rnd.nextInt(listsize - 1);
		}
		return index;
	}

	public String getRandomListElement(String combobox) {
		List<String> list = app.getContactHelper()
				.getListFromCombobox(combobox);
		int index = getRandomIndexOfList(list.size());
		String value = app.getContactHelper().getListElementByIndex(combobox,
				index);
		return value;
	}

	public GroupData getRandomGroup(String combobox) {
		List<GroupData> list = app.getContactHelper().getGroupListOfCombobox(
				combobox);
		int index = getRandomIndexOfList(list.size());
		return list.get(index);
	}

	public String generateRandomYear() {
		Random rnd = new Random();
		String randomYear = "";
		if (rnd.nextInt(3) == 0) {
		} else {
			for (int i = 0; i < 5; i++) {
				if (rnd.nextInt(5) == 0) {
				} else {
					randomYear = randomYear + rnd.nextInt(9);
				}
			}

		}

		return randomYear;
	}

	public String generateRandomString() {
		Random rnd = new Random();
		String randomString = null;
		if (rnd.nextInt(3) == 0) {
		} else {
			if (rnd.nextInt(3) == 0) {
				randomString = "";
			} else {
				randomString = "test" + rnd.nextInt();
			}

		}

		return randomString;
	}

}
