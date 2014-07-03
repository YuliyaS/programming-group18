package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.tests.GroupData;

public class ContactHelper extends HelperBase {

	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	private List<ContactData> cachedContacts;

	public List<ContactData> getContacts() {
		if (cachedContacts == null) {
			rebuildCache();
		}
		return cachedContacts;

	}

	private ContactHelper rebuildCache() {
		cachedContacts = new ArrayList<ContactData>();
		manager.navigateTo().mainPage();
		List<WebElement> rows = getContactTable();

		for (WebElement row : rows) {
			int index = rows.indexOf(row);
			ContactData contact = getContactByIndex(index);
			cachedContacts.add(contact);
		}
		return this;

	}

	public void createContact(ContactData contact) {
		manager.navigateTo().mainPage();
		initContactCreation();
		fillContactForm(contact, CREATION);
		submitContactCreation();
		returnToHomePage();
		rebuildCache();
	}

	public void modifyContact(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		initContactModification(index);
		fillContactForm(contact, MODIFICATION);
		submitContactModification();
		returnToHomePage();
		rebuildCache();
	}

	public void deleteContact(int index) {
		manager.navigateTo().mainPage();
		initContactModification(index);
		submitContactDeletion();
		returnToHomePage();
		rebuildCache();
	}

	public void addSomeContactToGroup(int contactIndex, int groupIndex,
			String groupName) {
		manager.navigateTo().mainPage();
		selectContactByIndex(contactIndex);
		selectGroupFromList(groupIndex);
		submitAddContactsToGroup();
		gotoContactListOfGroupByLink(groupName);
	}

	public void removeAllContactsFromGroup(String groupName) {
		manager.navigateTo().mainPage();
		openContactListOfGroup(groupName);
		selectAllContactsOnPage();
		submitContactRemovalFromGroup();
		gotoContactListOfGroupByLink(groupName);
	}

	// ----------------------------------------------------------------------------------------------------------------------

	private List<WebElement> getContactTable() {
		List<WebElement> rows = getListWebElements(By
				.xpath("(//tr[@name='entry'])"));
		return rows;
	}

	public ContactData getContactByIndex(int index) {
		int id = Integer.parseInt(getWebElement(
				By.xpath("(//input[@name='selected[]'])[" + (index + 1) + "]"))
				.getAttribute("value"));
		String lastname = getWebElement(
				By.xpath("(//td[2])[" + (index + 1) + "]")).getText();
		String firstname = getWebElement(
				By.xpath("(//td[3])[" + (index + 1) + "]")).getText();
		String email1 = getWebElement(
				By.xpath("(//td[4])[" + (index + 1) + "]/a")).getText();
		return new ContactData().withId(id).withLastname(lastname)
				.withFirstname(firstname).withEmail1(email1);
	}

	public ContactHelper submitContactCreation() {
		click(By.name("submit"));
		cachedContacts = null;
		return this;

	}

	public ContactHelper initContactCreation() {
		manager.navigateTo().mainPage();
		click(By.linkText("add new"));
		return this;
	}

	public ContactHelper initContactModification(int index) {
		manager.navigateTo().mainPage();
		click(By.xpath("(//img[@alt='Edit'])[" + (index + 1) + "]"));
		return this;
	}

	public ContactHelper fillContactForm(ContactData contact, boolean formType) {
		if (contact.getFirstname() != null) {
			type(By.name("firstname"), contact.getFirstname());
		}
		if (contact.getLastname() != null) {
			type(By.name("lastname"), contact.getLastname());
		}
		if (contact.getAddress1() != null) {
			type(By.name("address"), contact.getAddress1());
		}
		if (contact.getHomePhone1() != null) {
			type(By.name("home"), contact.getHomePhone1());
		}
		if (contact.getMobilePhone() != null) {
			type(By.name("mobile"), contact.getMobilePhone());
		}
		if (contact.getWorkPhone() != null) {
			type(By.name("work"), contact.getWorkPhone());
		}
		if (contact.getEmail1() != null) {
			type(By.name("email"), contact.getEmail1());
		}
		if (contact.getEmail2() != null) {
			type(By.name("email2"), contact.getEmail2());
		}
		if (contact.getBday() != null) {
			selectByText(By.name("bday"), contact.getBday());
		}
		if (contact.getBmonth() != null) {
			selectByText(By.name("bmonth"), contact.getBmonth());
		}
		if (contact.getByear() != null) {
			type(By.name("byear"), contact.getByear());
		}

		if (formType == CREATION) {
			if (contact.getGroup() != null) {
				selectByText(By.name("new_group"), contact.getGroup());
			}

		} else {
			if (getListWebElements(By.name("new_group")).size() != 0) {
				throw new Error(
						"Group selector exists in contact modification form");
			}
		}
		if (contact.getAddress2() != null) {
			type(By.name("address2"), contact.getAddress2());
		}
		if (contact.getHomePhone2() != null) {
			type(By.name("phone2"), contact.getHomePhone2());
		}
		return this;
	}

	public ContactData transformContactToVisibleOnContactsPage(
			ContactData contact, ContactData oldContact, boolean formType) {
		String lastname = contact.getLastname();
		String firstname = contact.getFirstname();
		String email1 = contact.getEmail1();
		String email2 = contact.getEmail2();
		int id;

		if (formType == CREATION) {
			id = 999999999;
			if (lastname == null) {
				lastname = "";
			}
			if (firstname == null) {
				firstname = "";
			}
			if ((email1 == null) || (email1.equals(""))) {
				if (email2 == null) {
					email2 = "";
				}
				email1 = email2;
			}
		} else {
			id = oldContact.getId();
			if (lastname == null) {
				lastname = oldContact.getLastname();
			}

			if (firstname == null) {
				firstname = oldContact.getFirstname();
			}

			if (email1 == null) {
				email1 = oldContact.getEmail1();
			}

			if (email1.equals("")) {
				if (email2 == null) {
					email2 = oldContact.getEmail2();
				}
				email1 = email2;
			}
		}
		return new ContactData().withId(id).withLastname(lastname)
				.withFirstname(firstname).withEmail1(email1);
	}

	public ContactHelper returnToHomePage() {
		click(By.linkText("home page"));
		return this;
	}

	public ContactHelper submitContactDeletion() {
		click(By.xpath("(//input[@name='update'])[2]"));
		cachedContacts = null;
		return this;

	}

	public ContactHelper submitContactModification() {
		click(By.name("update"));
		cachedContacts = null;
		return this;

	}

	public ContactHelper submitAddContactsToGroup() {
		click(By.name("add"));
		cachedContacts = null;
		return this;

	}

	private ContactHelper selectGroupFromList(int index) {
		click(By.xpath("//select[@name='to_group']/option[" + (index + 1) + "]"));
		return this;
	}

	public ContactHelper selectContactByIndex(int index) {
		manager.navigateTo().mainPage();
		click(By.xpath("(//input[@name='selected[]'])[" + (index + 1) + "]"));
		return this;
	}

	public ContactHelper selectAllContactsOnPage() {
		click(By.id("MassCB"));
		return this;
	}

	public ContactHelper gotoContactListOfGroupByLink(String groupname) {
		click(By.linkText("group page \"" + groupname + "\""));
		return this;

	}

	public ContactHelper openContactListOfGroup(int index) {
		manager.navigateTo().mainPage();
		click(By.xpath("//select[@name='group']/option[" + (index + 1) + "]"));
		cachedContacts = null;
		return this;
	}

	public ContactHelper openContactListOfGroup(String name) {
		manager.navigateTo().mainPage();
		selectByText(By.name("group"), name);
		cachedContacts = null;
		return this;
	}

	public ContactHelper submitContactRemovalFromGroup() {
		click(By.name("remove"));
		cachedContacts = null;
		return this;

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
			group.withName(option.getText());
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
			String name = getWebElement(
					By.xpath("//*[@id='content']/i/a[" + (groupIndex + 1) + "]"))
					.getText();

			if (name.equals(null)) {

				name = "";
			}

			groups.add(new GroupData().withName(name));
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

	public String getListElementByIndex(String combobox, int index) {
		List<String> list = getListFromCombobox(combobox);
		String value = list.get(index);
		return value;
	}

	public ContactData getContactDataOnEditingForm(int index) {
		initContactModification(index);
		int id = Integer
				.parseInt(getWebElement(By.xpath("//input[@name='id']"))
						.getAttribute("value"));
		String lastname = getWebElement(By.xpath("//input[@name='lastname']"))
				.getAttribute("value");
		String firstname = getWebElement(By.xpath("//input[@name='firstname']"))
				.getAttribute("value");
		String email1 = getWebElement(By.xpath("//input[@name='email']"))
				.getAttribute("value");
		String email2 = getWebElement(By.xpath("//input[@name='email2']"))
				.getAttribute("value");
		return new ContactData().withId(id).withLastname(lastname)
				.withFirstname(firstname).withEmail1(email1).withEmail2(email2);
	}

}
