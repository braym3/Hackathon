package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	Order order = new Order(1L, "test", 1L);

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}

	@Test
	public void testIdSet()
	{
		Long testLong = 2l;
		order.setId(testLong);
		assertEquals(testLong, order.getId());
	}

	@Test
	public void testNameSet()
	{
		String testLong = "bah";
		order.setOrderName(testLong);
		assertEquals(testLong, order.getOrderName());
	}

	@Test
	public void testCustIdSet()
	{
		Long testLong = 2l;
		order.setCustomerId(testLong);
		assertEquals(testLong, order.getCustomerId());
	}



}
