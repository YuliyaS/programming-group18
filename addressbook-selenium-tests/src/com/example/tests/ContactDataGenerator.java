package com.example.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class ContactDataGenerator extends RandomDataGenerator {

	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
			System.out
					.println("Please specify parameters: <amount of test data> <file> <format>");
			return;
		}

		int amount = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		String format = args[2];

		if (file.exists()) {
			System.out.println("File exists. Please, remove it manually: "
					+ file + ".");
			return;
		}

		List<ContactData> contacts = generateRandomContacts(amount);
		if ("csv".equals(format)) {
			saveContactsToCsvFile(contacts, file);
		} else if ("xml".equals(format)) {
			saveContactsToXmlFile(contacts, file);
		} else {
			System.out.println("Unknown format " + format);
			return;
		}

	}

	private static void saveContactsToXmlFile(List<ContactData> contacts,
			File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		String xml = xstream.toXML(contacts);
		FileWriter writer = new FileWriter(file);
		writer.write(xml);
		writer.close();
	}

	private static void saveContactsToCsvFile(List<ContactData> contacts,
			File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		for (ContactData contact : contacts) {
			writer.write(contact.getFirstname() + "," + contact.getLastname()
					+ "," + contact.getAddress1() + ","
					+ contact.getHomePhone1() + "," + contact.getMobilePhone()
					+ "," + contact.getWorkPhone() + "," + contact.getEmail1()
					+ "," + contact.getEmail2() + "," + contact.getAddress2()
					+ "," + contact.getHomePhone2() + "," + contact.getBday()
					+ "," + contact.getBmonth() + "," + contact.getByear()
					+ "," + contact.getGroup() + ",!" + "\n");
		}
		writer.close();
	}

	public static List<ContactData> loadContactsFromCsvFile(File file)
			throws IOException {
		List<ContactData> list = new ArrayList<ContactData>();
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] part = line.split(",");
			for (int i = 0; i < part.length; i++) {
				if (part[i].equals("null")) {
					part[i] = null;
				}
			}
			ContactData contact = new ContactData().withFirstname(part[0])
					.withLastname(part[1]).withAddress1(part[2])
					.withHomePhone1(part[3]).withMobilePhone(part[4])
					.withWorkPhone(part[5]).withEmail1(part[6])
					.withEmail2(part[7]).withAddress2(part[8])
					.withHomePhone2(part[9]).withBday(part[10])
					.withBmonth(part[11]).withByear(part[12])
					.withGroup(part[13]);
			list.add(contact);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return list;
	}

	public static List<ContactData> loadContactsFromXmlFile(File file) {
		XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		return (List<ContactData>) xstream.fromXML(file);
	}

	public static List<ContactData> generateRandomContacts(int amount)
			throws IOException {
		List<ContactData> list = new ArrayList<ContactData>();
		for (int i = 0; i < amount; i++) {
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
					.withGroup(getRandomGroupName());
			list.add(contact);
		}
		return list;
	}

}
