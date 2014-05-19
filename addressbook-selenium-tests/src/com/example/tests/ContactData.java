package com.example.tests;

public class ContactData {
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

	public ContactData(String firstname, String lastname, String address1,
			String home_phone1, String mobile_phone, String work_phone,
			String email1, String email2, String bday, String bmonth,
			String byear, String group, String address2, String home_phone2) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address1 = address1;
		this.home_phone1 = home_phone1;
		this.mobile_phone = mobile_phone;
		this.work_phone = work_phone;
		this.email1 = email1;
		this.email2 = email2;
		this.bday = bday;
		this.bmonth = bmonth;
		this.byear = byear;
		this.group = group;
		this.address2 = address2;
		this.home_phone2 = home_phone2;
	}

	public ContactData() {

	}
}