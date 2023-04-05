package com.qa.ims.persistence.domain;

public class Order {

	private Long id, customerId, driverId;
	private Long delivered, warehouseId;

	public Order(Long customerId) {
		this.customerId = customerId;
		}

	public Order(Long id, Long customerId) {
		this.id = id;
		this.customerId = customerId;
	}
	

	public Order(Long customerId, Long driverId, Long delivered, Long warehouseId) {
		this.customerId = customerId;
		this.driverId = driverId;
		this.delivered = delivered;
		this.warehouseId = warehouseId;
	}

	public Order(Long id, Long customerId, Long driverId, Long delivered, Long warehouseId) {
		this.id = id;
		this.customerId = customerId;
		this.driverId = driverId;
		this.delivered = delivered;
		this.warehouseId = warehouseId;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getDelivered() {
		return delivered;
	}

	public void setDelivered(Long delivered) {
		this.delivered = delivered;
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

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", driverId=" + driverId + ", delivered=" + delivered
				+ ", warehouseId=" + warehouseId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result + ((delivered == null) ? 0 : delivered.hashCode());
		result = prime * result + ((warehouseId == null) ? 0 : warehouseId.hashCode());
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
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		if (delivered == null) {
			if (other.delivered != null)
				return false;
		} else if (!delivered.equals(other.delivered))
			return false;
		if (warehouseId == null) {
			if (other.warehouseId != null)
				return false;
		} else if (!warehouseId.equals(other.warehouseId))
			return false;
		return true;
	}



}
