package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Warehouse {
	
	private Long id;
	private String name;
	private String postcode;
	
	public Warehouse(String name, String postcode) {
		this.setName(name);
		this.setPostcode(postcode);
	}
	
	public Warehouse(Long id, String name, String postcode) {
		this.setId(id);
		this.setName(name);
		this.setPostcode(postcode);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, postcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Warehouse other = (Warehouse) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(postcode, other.postcode);
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", name=" + name + ", postcode=" + postcode + "]";
	}
	
	
	
}
