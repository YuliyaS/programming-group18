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
			GroupData group = new GroupData().withName(generateRandomString())
					.withHeader(generateRandomString())
					.withFooter(generateRandomString());
			list.add(new Object[] { group });
		}
		return list.iterator();
	}

	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		app.navigateTo().mainPage();

		for (int i = 0; i < 5; i++) {
			String DOB = RandomDOB();
			ContactData contact = new ContactData()
					.withFirstname(generateRandomString())
					.withLastname(generateRandomString())
					.withAddress1(generateRandomString())
					.withHomePhone1(generateRandomString())
					.withMobilePhone(generateRandomString())
					.withWorkPhone(generateRandomString())
					.withEmail1(generateRandomString())
					.withEmail2(generateRandomString())
					.withAddress2(generateRandomString())
					.withHomePhone2(generateRandomString())
					.withBday(randomBday(DOB)).withBmonth(randomBmonth(DOB))
					.withByear(randomByear(DOB))
					.withGroup(getRandomGroup().getName());
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
			group.withName(null);
		} else {
			if (rnd.nextInt(3) == 0) {
				group.withName("[none]");
			} else {

				app.navigateTo().groupsPage();
				List<GroupData> list = app.getGroupHelper().getGroups();
				int index = getRandomIndexOfList(list.size());
				group = list.get(index);
			}
		}

		return group;
	}
	
	public String getRandomNonEmptyGroupName() {
		List<GroupData> groupList = app.getGroupHelper().getGroups();
		String groupName;
		int groupIndex;

		do {
			groupIndex = getRandomIndexOfList(groupList.size());
			groupName = groupList.get(groupIndex).getName();
		} while (groupName.equals(""));
		
		return groupName;
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

	public String randomBday(String DOB) {
		Random rnd = new Random();
		String bday = null;
		if (rnd.nextInt(5) == 0) {
		} else {
			if (rnd.nextInt(5) == 0) {
				bday = "-";
			} else {
				String[] DMY = DOB.split("/");
				bday = DMY[0];
			}

		}
		return bday;
	}

	public String randomBmonth(String DOB) {
		Random rnd = new Random();
		String bmonth = null;
		if (rnd.nextInt(5) == 0) {
		} else {
			if (rnd.nextInt(5) == 0) {
				bmonth = "-";
			} else {
				String[] DMY = DOB.split("/");
				bmonth = DMY[1];
			}

		}
		return bmonth;
	}

	public String randomByear(String DOB) {
		Random rnd = new Random();
		String byear = null;
		if (rnd.nextInt(5) == 0) {
		} else {
			if (rnd.nextInt(5) == 0) {
				byear = "";
			} else {
				String[] DMY = DOB.split("/");
				byear = DMY[2];
			}
		}

		return byear;
	}

	public String RandomDOB() {
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
