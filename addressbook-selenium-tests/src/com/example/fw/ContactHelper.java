package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.tests.GroupData;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void submitContactCreation() {
		click(By.name("submit"));

	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	}

	public void fillContactForm(ContactData contact) {
		if (contact.firstname != null) {
			type(By.name("firstname"), contact.firstname);
		}
		if (contact.lastname != null) {
			type(By.name("lastname"), contact.lastname);
		}
		if (contact.address1 != null) {
			type(By.name("address"), contact.address1);
		}
		if (contact.home_phone1 != null) {
			type(By.name("home"), contact.home_phone1);
		}
		if (contact.mobile_phone != null) {
			type(By.name("mobile"), contact.mobile_phone);
		}
		if (contact.work_phone != null) {
			type(By.name("work"), contact.work_phone);
		}
		if (contact.email1 != null) {
			type(By.name("email"), contact.email1);
		}
		if (contact.email2 != null) {
			type(By.name("email2"), contact.email2);
		}
		if (contact.bday != null) {
			selectByText(By.name("bday"), contact.bday);
		}
		if (contact.bmonth != null) {
			selectByText(By.name("bmonth"), contact.bmonth);
		}
		if (contact.byear != null) {
			type(By.name("byear"), contact.byear);
		}
		if (contact.group != null) {
			selectByText(By.name("new_group"), contact.group);
		}
		if (contact.address2 != null) {
			type(By.name("address2"), contact.address2);
		}
		if (contact.home_phone2 != null) {
			type(By.name("phone2"), contact.home_phone2);
		}
	}

	public void returnToHomePage() {
		click(By.linkText("home page"));
	}

	public void submitContactDeletion() {
		click(By.xpath("(//input[@name='update'])[2]"));

	}

	public void initContactModification(int index) {
		click(By.xpath("(//img[@alt='Edit'])[" + (index + 1) + "]"));
	}

	public void submitContactModification() {
		click(By.name("update"));

	}

	public void addContactsToGroup(int index) {
		click(By.xpath("//select[@name='to_group']/option[" + (index + 1) + "]"));
		click(By.name("add"));

	}

	public void selectContactByIndex(int index) {
		click(By.xpath("(//input[@name='selected[]'])[" + (index + 1) + "]"));
	}

	public void selectAllContactsOnPage() {
		click(By.id("MassCB"));
	}

	public void gotoContactListOfGroupByLink(String groupname) {
		click(By.linkText("group page \"" + groupname + "\""));

	}

	public void openContactListOfGroup(int index) {
		click(By.xpath("//select[@name='group']/option[" + (index + 1) + "]"));
	}

	public void openContactListOfGroup(String name) {
		selectByText(By.name("group"), name);

	}

	public void removeContactsFromGroup() {
		selectAllContactsOnPage();
		click(By.name("remove"));

	}

	public List<ContactData> getContacts() {

		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> rows = driver.findElements(By
				.xpath("(//tr[@name='entry'])"));

		for (WebElement row : rows) {
			int index = rows.indexOf(row);
			ContactData contact = getContactByIndex(index);
			contacts.add(contact);
		}

		return contacts;
	}

	public ContactData getContactByIndex(int index) {
		ContactData contact = new ContactData();
		contact.lastname = getWebElement(
				By.xpath("(//td[2])[" + (index + 1) + "]")).getText();
		contact.firstname = getWebElement(
				By.xpath("(//td[3])[" + (index + 1) + "]")).getText();
		contact.email1 = getWebElement(
				By.xpath("(//td[4])[" + (index + 1) + "]/a")).getText();
		return contact;
	}

	private String switchCombobox(String combobox)

	{
		String choiceCombobox;
		switch (combobox) {
		case "groupListOnMainPage":
			choiceCombobox = "//select[@name='group']/option";
			break;
		case "addToGroupList":
			choiceCombobox = "//select[@name='to_group']/option";
			break;
		case "groupListOnAddContactPage":
			choiceCombobox = "//select[@name='new_group']/option";
			break;
		case "bday":
			choiceCombobox = "//select[@name='bday']/option";
			break;
		case "bmonth":
			choiceCombobox = "//select[@name='bmonth']/option";
			break;
		default:
			choiceCombobox = null;
			break;
		}
		return choiceCombobox;

	}

	public List<GroupData> getGroupListOfCombobox(String combobox)

	{
		String choiceCombobox = switchCombobox(combobox);
		List<GroupData> groups = new ArrayList<GroupData>();
		List<WebElement> options = getListWebElements(By.xpath(choiceCombobox));
		for (WebElement option : options) {
			GroupData group = new GroupData();
			group.name = option.getText();
			groups.add(group);

		}
		return groups;
	}

	public List<GroupData> getGroupListOfContact(int index) {
		click(By.xpath("(//img[@alt='Details'])[" + (index + 1) + "]"));
		List<GroupData> groups = new ArrayList<GroupData>();
		List<WebElement> groupnames = getListWebElements(By
				.xpath(".//*[@id='content']/i/a"));

		for (WebElement groupname : groupnames) {
			int groupIndex = groupnames.indexOf(groupname);
			GroupData group = new GroupData();
			group.name = getWebElement(
					By.xpath("//*[@id='content']/i/a[" + (groupIndex + 1) + "]"))
					.getText();

			if (group.name.equals(null)) {

				group.name = "";
			}

			groups.add(group);
		}

		return groups;

	}

	public List<String> getListFromCombobox(String combobox) {
		String choiceCombobox = switchCombobox(combobox);
		List<String> list = new ArrayList<String>();
		List<WebElement> options = getListWebElements(By.xpath(choiceCombobox));
		for (WebElement option : options) {
			String value = option.getText();
			list.add(value);

		}
return list;
	}
	
	public String getListElementByIndex(String combobox, int index) 
	{
		List<String> list = getListFromCombobox(combobox);
		String value = list.get(index);
		return value;
	}

}
