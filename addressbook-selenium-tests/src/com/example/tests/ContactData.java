package com.example.tests;

public class ContactData implements Comparable<ContactData> {
	private int id;
	private String firstname;
	private String lastname;
	private String address1;
	private String homePhone1;
	private String mobilePhone;
	private String workPhone;
	private String email1;
	private String email2;
	private String bday;
	private String bmonth;
	private String byear;
	private String group;
	private String address2;
	private String homePhone2;
	private String visibleEmail;

	public ContactData() {

	}

	@Override
	public String toString() {
		return "ContactData [firstname=" + firstname + ", lastname=" + lastname
				+ ", visibleEmail=" + visibleEmail + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = prime * result + ((lastname == null) ? 0 :
		// lastname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactData other = (ContactData) obj;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (other.visibleEmail == null) {
			return true;
		} else {
			if (visibleEmail == null) {
				if (other.visibleEmail != null)
					return false;
			} else if (!visibleEmail.equals(other.visibleEmail))
				return false;
			return true;
		}

	}

	@Override
	public int compareTo(ContactData other) {
		int result = this.lastname.toLowerCase().compareTo(
				other.lastname.toLowerCase());
		if (result != 0) {
			return result;

		} else {
			result = this.firstname.toLowerCase().compareTo(
					other.firstname.toLowerCase());
			if (result != 0) {
				return result;

			} else {
				result = (this.id - other.id);
				return result;

			}

		}

	}

	public ContactData withId(int id) {
		this.id = id;
		return this;
	}

	public ContactData withFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public ContactData withLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public ContactData withAddress1(String address1) {
		this.address1 = address1;
		return this;
	}

	public ContactData withHomePhone1(String homePhone1) {
		this.homePhone1 = homePhone1;
		return this;
	}

	public ContactData withMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public ContactData withWorkPhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public ContactData withEmail1(String email1) {
		this.email1 = email1;
		return this;

	}

	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}

	public ContactData withVisibleEmail(String visibleEmail) {
		this.visibleEmail = visibleEmail;
		return this;

	}

	public ContactData withAddress2(String address2) {
		this.address2 = address2;
		return this;
	}

	public ContactData withHomePhone2(String homePhone2) {
		this.homePhone2 = homePhone2;
		return this;
	}

	public ContactData withBday(String bday) {
		this.bday = bday;
		return this;
	}

	public ContactData withBmonth(String bmonth) {
		this.bmonth = bmonth;
		return this;
	}

	public ContactData withByear(String byear) {
		this.byear = byear;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getAddress1() {
		return address1;
	}

	public String getHomePhone1() {
		return homePhone1;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getEmail1() {
		return email1;
	}

	public String getEmail2() {
		return email2;
	}

	public String getBday() {
		return bday;
	}

	public String getBmonth() {
		return bmonth;
	}

	public String getByear() {
		return byear;
	}

	public String getGroup() {
		return group;
	}

	public String getAddress2() {
		return address2;
	}

	public String getHomePhone2() {
		return homePhone2;
	}

	public String getVisibleEmail() {
		return visibleEmail;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setHomePhone1(String homePhone1) {
		this.homePhone1 = homePhone1;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public void setBmonth(String bmonth) {
		this.bmonth = bmonth;
	}

	public void setByear(String byear) {
		this.byear = byear;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setHomePhone2(String homePhone2) {
		this.homePhone2 = homePhone2;
	}

	public void setVisibleEmail(String visibleEmail) {
		this.visibleEmail = visibleEmail;
	}

}
