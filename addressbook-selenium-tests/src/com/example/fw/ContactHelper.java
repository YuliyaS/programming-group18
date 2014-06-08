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
		// selectByText(By.name("new_group"), contact.group);
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

	public void addContactToGroup(String groupname) {
		selectAllContactsOnPage();
		selectByText(By.name("to_group"), groupname);
		click(By.name("add"));

	}

	private void selectAllContactsOnPage() {
		click(By.id("MassCB"));
	}

	public void gotoContactListOfGroupByLink(String groupname) {
		click(By.linkText("group page \"" + groupname + "\""));

	}

	public void openContactListOfGroup(String groupname) {
		selectByText(By.name("group"), groupname);

	}

	public void RemoveContactFromGroup() {
		selectAllContactsOnPage();
		click(By.name("remove"));

	}
	
		public List<ContactData> getContacts() {
			
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
		
		for (WebElement checkbox : checkboxes) {
			int index = checkboxes.indexOf(checkbox);
			ContactData contact = getContactByIndex(index);
			contacts.add(contact);
		}	
		
		return contacts;
	}

		public ContactData getContactByIndex(int index) {
			int rowNumber = index + 2;
			ContactData contact = new ContactData();
			contact.lastname = driver.findElement(
					By.xpath("//tr[" + rowNumber + "]/td[2]")).getText();
			contact.firstname = driver.findElement(
					By.xpath("//tr[" + rowNumber + "]/td[3]")).getText();
			contact.email1 = driver.findElement(
					By.xpath("//tr[" + rowNumber + "]/td[4]/a")).getText();
			contact.home_phone1 = driver.findElement(
					By.xpath("//tr[" + rowNumber + "]/td[5]")).getText();
			return contact;
		}

		public List<GroupData> getGroupListOfCombobox(String combobox) {
			String choiceCombobox = null;
			if (combobox == "listOnMainPage") {choiceCombobox = "//select/option";
				
			}
			if (combobox == "addToGroupList") {choiceCombobox = "//select[3]/option";
			
			}
			
			if (combobox == "listOnAddContactPage") {choiceCombobox = "//select[3]/option";
			
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
		


}
