package com.example.fw;

public class Contact {

	public String firsname;
	public String lastname;

	public Contact setFirstName(String firsname) {
		this.firsname = firsname;
		return this;
	}

	public Contact setLastName(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public void setFirsname(String firsname) {
		this.firsname = firsname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirsname() {
		return firsname;
	}

	public String getLastname() {
		return lastname;
	}

}
