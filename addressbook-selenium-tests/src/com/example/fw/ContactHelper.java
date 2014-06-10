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
		type(By.name("firstname"), contact.firstname);
		type(By.name("lastname"), contact.lastname);
		type(By.name("address"), contact.address1);
		type(By.name("home"), contact.home_phone1);
		type(By.name("mobile"), contact.mobile_phone);
		type(By.name("work"), contact.work_phone);
		type(By.name("email"), contact.email1);
		type(By.name("email2"), contact.email2);
		selectByText(By.name("bday"), contact.bday);
		selectByText(By.name("bmonth"), contact.bmonth);
		type(By.name("byear"), contact.byear);
		selectByText(By.name("new_group"), contact.group);
		type(By.name("address2"), contact.address2);
		type(By.name("phone2"), contact.home_phone2);
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
		List<WebElement> checkboxes = driver
				.findElements(By.name("selected[]"));

		for (WebElement checkbox : checkboxes) {
			int index = checkboxes.indexOf(checkbox);
			ContactData contact = getContactByIndex(index);
			contacts.add(contact);
		}

		return contacts;
	}

	private ContactData getContactByIndex(int index) {
		ContactData contact = new ContactData();
		contact.lastname = driver.findElement(
				By.xpath("(//td[2])[" + (index + 1) + "]")).getText();
		contact.firstname = driver.findElement(
				By.xpath("(//td[3])[" + (index + 1) + "]")).getText();
		contact.email1 = driver.findElement(
				By.xpath("(//td[4])[" + (index + 1) + "]/a")).getText();
		return contact;
	}

	public List<GroupData> getGroupListOfCombobox(String combobox)

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
		default:
			choiceCombobox = null;
			break;

		}
		List<GroupData> groups = new ArrayList<GroupData>();
		List<WebElement> options = driver
				.findElements(By.xpath(choiceCombobox));
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
		List<WebElement> groupnames = driver
				.findElements(By.xpath(".//*[@id='content']/i/a"));

		for (WebElement groupname : groupnames) {
			int groupIndex = groupnames.indexOf(groupname);
			GroupData group = new GroupData();
			group.name = driver.findElement(
					By.xpath("//*[@id='content']/i/a[" + (groupIndex + 1) + "]")).getText();
			groups.add(group);
		}

		return groups;

	}

}
