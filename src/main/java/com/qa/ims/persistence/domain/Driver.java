package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Driver {
	
	private Long id;
	private String firstName;
	private String surname;
	private ArrayList<Delivery> deliveries;
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
	public ArrayList<Delivery> getDeliveries() {
		return deliveries;
	}
	public void setDeliveries(ArrayList<Delivery> deliveries) {
		this.deliveries = deliveries;
	}
	
	public boolean containsDelivery(Long deliveryID) {
		for (Delivery delivery : deliveries) {
			if (delivery.getId() == deliveryID) {
				return true;
			}
		}
		return false;
	}
	
	public Driver addDelivery(Delivery delivery) {
		this.deliveries.add(delivery);
		return this;
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, surname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(surname, other.surname);
	}
	@Override
	public String toString() {
		return "Driver [id=" + id + ", firstName=" + firstName + ", surname=" + surname + "]";
	}
	
	
}
