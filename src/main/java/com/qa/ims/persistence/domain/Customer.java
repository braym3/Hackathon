package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Customer {

	private Long id;
	private String firstName;
	private String surname;
	private String address;
	private String postcode;

	public Customer(String firstName, String surname, String postcode) {
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setPostcode(postcode);
	}

	public Customer(Long id, String firstName, String surname, String postcode) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setPostcode(postcode);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, firstName, id, postcode, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(postcode, other.postcode)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", address=" + address
				+ ", postcode=" + postcode + "]";
	}

	

}
