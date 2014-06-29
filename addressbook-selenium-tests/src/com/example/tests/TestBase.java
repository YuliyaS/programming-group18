package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

import java.util.Calendar;

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
			contact.address2 = generateRandomString();
			contact.home_phone2 = generateRandomString();
			String DOB = randomDOB();
			String[] DMY = DOB.split("/");
			String bday = DMY[0];
			String bmonth = DMY[1];
			String byear = DMY[2];
			if (bday.equals("null")) {
				contact.bday = null;
			} else {
				contact.bday = bday;
			}
			if (bmonth.equals("null")) {
				contact.bmonth = null;
			} else {
				contact.bmonth = bmonth;
			}
			if (byear.equals("null")) {
				contact.byear = null;
			} else {
				if (byear.equals(" ")) {
					contact.byear = "";
				} else {
					contact.byear = byear;
				}

			}

			contact.group = getRandomGroup().name;

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

	public GroupData getRandomGroup() {
		GroupData group = new GroupData();
		Random rnd = new Random();
		if (rnd.nextInt(3) == 0) {
			group.name = null;
		} else {
			app.getNavigationHelper().gotoGroupsPage();
			List<GroupData> list = app.getGroupHelper().getGroups();
			app.getGroupHelper().transformListGroups(list);
			int index = getRandomIndexOfList(list.size());
			group = list.get(index);
		}
		return group;
	}

	public String generateRandomString() {
		Random rnd = new Random();
		String randomString = null;
		if (rnd.nextInt(3) == 0) {
		} else {
			if (rnd.nextInt(3) == 0) {
				randomString = "";
			} else {

				randomString = generateRandomSimbol() + "test" + rnd.nextInt();
			}

		}

		return randomString;
	}

	public char generateRandomSimbol() {
		Random rnd = new Random();
		String str = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		int index = rnd.nextInt(61);
		char randomSimbol = str.charAt(index);
		return randomSimbol;
	}

	public static String randomDOB() {
		Random rnd = new Random();
		int yyyy = random(1900, 2013);
		int mm = random(1, 12);
		int dd = 0;
		String month = null;
		switch (mm) {
		case 2:
			month = "February";
			if (isLeapYear(yyyy)) {
				dd = random(1, 29);
			} else {
				dd = random(1, 28);
			}
			break;

		case 1:
			month = "January";
		case 3:
			month = "March";
		case 5:
			month = "May";
		case 7:
			month = "July";
		case 8:
			month = "August";
		case 10:
			month = "October";
		case 12:
			month = "December";
			dd = random(1, 31);
			break;

		case 4:
			month = "April";
		case 6:
			month = "June";
		case 9:
			month = "September";
		case 11:
			month = "November";
			dd = random(1, 30);
			break;

		default:
			dd = random(1, 28);
			break;
		}

		String year = Integer.toString(yyyy);
		String day = Integer.toString(dd);

		if (rnd.nextInt(4) == 0) {
			day = null;
		} else {
			if (rnd.nextInt(4) == 0) {
				day = "-";
			}
		}

		if (rnd.nextInt(4) == 0) {
			month = null;
		} else {
			if (rnd.nextInt(4) == 0) {
				month = "-";
			}
		}

		if (rnd.nextInt(4) == 0) {
			year = null;
		} else {
			if (rnd.nextInt(4) == 0) {
				year = " ";
			}
		}

		return day + '/' + month + '/' + year;
	}

	private static int random(int lowerBound, int upperBound) {
		return (lowerBound + (int) Math.round(Math.random()
				* (upperBound - lowerBound)));
	}

	private static boolean isLeapYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		int noOfDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

		if (noOfDays > 365) {
			return true;
		}

		return false;
	}
}
