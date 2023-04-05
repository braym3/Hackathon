package com.qa.ims.persistence.domain;

import java.util.ArrayList;

public class Driver {
	
	private Long id;
	private String firstName;
	private String surname;
	private ArrayList<Order> orders;
	private Long warehouseID;
	
	public Driver(String firstName, String surname, Long warehouseID) {
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setWarehouseID(warehouseID);
		
	}

	public Driver(Long id, String firstName, String surname, Long warehouseID) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setWarehouseID(warehouseID);
	}
	
	public Driver(Long id, String firstName, String surname, Long warehouseID, ArrayList<Order> orders) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setWarehouseID(warehouseID);
		this.setDeliveries(orders);
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
	public ArrayList<Order> getDeliveries() {
		return orders;
	}
	public void setDeliveries(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	public boolean containsOrder(Long orderID) {
		for (Order order : orders) {
			if (order.getId() == orderID) {
				return true;
			}
		}
		return false;
	}
	
	public Driver addOrder(Order order) {
		this.orders.add(order);
		return this;
	}

	public Long getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(Long warehouseID) {
		this.warehouseID = warehouseID;
	}
	
	
	
}
