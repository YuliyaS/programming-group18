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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firsname == null) ? 0 : firsname.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
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
		Contact other = (Contact) obj;
		if (firsname == null) {
			if (other.firsname != null)
				return false;
		} else if (!firsname.equals(other.firsname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [firsname=" + firsname + ", lastname=" + lastname + "]";
	}

	
}
