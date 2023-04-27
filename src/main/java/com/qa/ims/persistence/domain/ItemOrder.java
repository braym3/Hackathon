package com.qa.ims.persistence.domain;

public class ItemOrder {

	private Long itemId, orderId, orderQuant;

	


	

	public ItemOrder(Long itemId, Long orderId, Long orderQuant) {
		this.itemId = itemId;
		this.orderId = orderId;
		this.orderQuant = orderQuant;
	}

	
	public Long getItemId() {
		return itemId;
	}


	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public Long getOrderQuant() {
		return orderQuant;
	}


	public void setOrderQuant(Long orderQuant) {
		this.orderQuant = orderQuant;
	}


	

	@Override
	public String toString() {
		return "ItemOrder [itemId=" + itemId + ", orderId=" + orderId + ", orderQuant=" + orderQuant + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((orderQuant == null) ? 0 : orderQuant.hashCode());
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
		ItemOrder other = (ItemOrder) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderQuant == null) {
			if (other.orderQuant != null)
				return false;
		} else if (!orderQuant.equals(other.orderQuant))
			return false;
		return true;
	}

}
