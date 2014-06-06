package com.example.tests;

public class ContactData implements Comparable<ContactData> {
	public String firstname;
	public String lastname;
	public String address1;
	public String home_phone1;
	public String mobile_phone;
	public String work_phone;
	public String email1;
	public String email2;
	public String bday;
	public String bmonth;
	public String byear;
	public String group;
	public String address2;
	public String home_phone2;

	public ContactData() {

	}
	
	

	@Override
	public String toString() {
		return "ContactData [firstname=" + firstname + ", lastname=" + lastname
				+ ", home_phone1=" + home_phone1 + ", email1=" + email1 + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
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
		if (home_phone1 == null) {
			if (other.home_phone1 != null)
				return false;
		} else if (!home_phone1.equals(other.home_phone1))
			return false;
		if (email1 == null) {
			if (other.email1 != null)
				return false;
		} else if (!email1.equals(other.email1))
			return false;
		return true;
	}


	@Override
	public int compareTo(ContactData other) {
		this.home_phone1.toLowerCase().compareTo(other.home_phone1.toLowerCase());
		this.email1.toLowerCase().compareTo(other.email1.toLowerCase());
		this.firstname.toLowerCase().compareTo(other.firstname.toLowerCase());
		return this.lastname.toLowerCase().compareTo(other.lastname.toLowerCase());
	}

	
}