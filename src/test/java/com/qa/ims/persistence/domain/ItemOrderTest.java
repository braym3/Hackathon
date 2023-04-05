package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemOrderTest {

	ItemOrder itemOrder = new ItemOrder(1L, 1L, 1L);

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(ItemOrder.class).verify();
	}

	@Test
	public void testIdSet()
	{
		Long testLong = 2l;
		itemOrder.setItemId(testLong);
		assertEquals(testLong, itemOrder.getItemId());
	}

	@Test
	public void testOrderIdSet()
	{
		Long testLong = 2L;
		itemOrder.setOrderId(testLong);
		assertEquals(testLong, itemOrder.getOrderId());
	}

	@Test
	public void testOrderQuantTest()
	{
		Long testLong = 2l;
		itemOrder.setOrderQuant(testLong);
		assertEquals(testLong, itemOrder.getOrderQuant());
	}

}
