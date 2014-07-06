package com.example.tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.example.fw.ApplicationManager;

public class RandomDataGenerator extends TestBase {

	public static int getRandomIndexOfList(int listsize) {
		Random rnd = new Random();
		int index;
		if (rnd.nextInt(3) == 0) {
			index = 0;
		} else {
			index = rnd.nextInt(listsize - 1);
		}
		return index;
	}

	public static int getRandomIndexOfGroupWithNonEmptyGroupName() {
		List<GroupData> groupList = app.getGroupHelper().getGroups();
		String groupName;
		int groupIndex;

		do {
			groupIndex = getRandomIndexOfList(groupList.size());
			groupName = groupList.get(groupIndex).getName();
		} while (groupName.equals(""));

		return groupIndex;
	}

	public static String generateRandomString() {
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

	public static String randomBday(String DOB) {
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

	public static String randomBmonth(String DOB) {
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

	public static String randomByear(String DOB) {
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

	public static String getRandomGroupName() {
		GroupData group = new GroupData();
		Random rnd = new Random();
		if (rnd.nextInt(3) == 0) {
			group.withName(null);
		} else {
			if (rnd.nextInt(3) == 0) {
				group.withName("[none]");
			} else {
				List<GroupData> groups = getTestGroupList();
				int index = getRandomIndexOfList(groups.size());
				group = groups.get(index);
			}
		}

		return group.getName();
	}

	private static char generateRandomSimbol() {
		Random rnd = new Random();
		String str = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		int index = rnd.nextInt(61);
		char randomSimbol = str.charAt(index);
		return randomSimbol;
	}

	public static String RandomDOB() {
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

	private static List<GroupData> getTestGroupList() {
		List<GroupData> groups = new ArrayList<GroupData>();
		if (app == null) {
			groups = runAppAndGetGroups();
		} else {
			groups = app.getGroupHelper().getGroups();
		}
		return groups;

	}

	private static List<GroupData> runAppAndGetGroups() {
		List<GroupData> groups = new ArrayList<GroupData>();
		app = new ApplicationManager();
		groups = app.getGroupHelper().getGroups();
		app.stop();
		return groups;

	}

}
