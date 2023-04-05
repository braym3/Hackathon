package com.qa.ims.persistence.domain;

public class Order {

	private Long id, customerId;
	private String orderName;

	public Order(String orderName, Long customerId) {
		this.customerId = customerId;
		this.orderName = orderName;
	}

	public Order(Long id, String orderName, Long customerId) {
		this.id = id;
		this.customerId = customerId;
		this.orderName = orderName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String firstName) {
		this.orderName = firstName;
	}

	@Override
	public String toString() {
		return "id:" + id + " orderName:" + orderName + " customerId:" + customerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderName == null) ? 0 : orderName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
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
		Order other = (Order) obj;
		if (getOrderName() == null) {
			if (other.getOrderName() != null)
				return false;
		} else if (!getOrderName().equals(other.getOrderName()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}

}
