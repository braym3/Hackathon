package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	Customer customer = new Customer(1L, "test", "test");

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

	@Test
	public void testIdSet()
	{
		Long testLong = 2l;
		customer.setId(testLong);
		assertEquals(testLong, customer.getId());
	}

	@Test
	public void testFirstNameSet()
	{
		String testLong = "bah";
		customer.setFirstName(testLong);
		assertEquals(testLong, customer.getFirstName());
	}

	@Test
	public void testSurnameSet()
	{
		String testLong = "bah";
		customer.setSurname(testLong);
		assertEquals(testLong, customer.getSurname());
	}

	

}
