package com.example.tests;

import java.io.File;
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
		for (ContactData contact: contacts) {
			writer.write(contact.getFirstname() + "," + contact.getLastname() + ","
					+ contact.getAddress1() + contact.getHomePhone1() + "," 
					+ contact.getMobilePhone() + "," + contact.getWorkPhone() + "," 
					+ contact.getEmail1() + "," + contact.getEmail2() + "," 
					+ contact.getAddress2() + "," + contact.getHomePhone2() + "," 
					+ contact.getBday() + "," + contact.getBmonth() + "," 
					+ contact.getByear() + "," + contact.getGroup() + ",!" + "\n");
		}
		writer.close();
	}


	public static List<ContactData> generateRandomContacts(int amount) {
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
					.withBday(randomBday(DOB))
					.withBmonth(randomBmonth(DOB))
					.withByear(randomByear(DOB))
					.withGroup(getRandomGroupName());
			list.add(contact);
		}
		return list;
	}
	
	
}
