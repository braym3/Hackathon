package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {

	Item item = new Item(1L, "null", 1L);

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}

	@Test
	public void testIdSet()
	{
		Long testLong = 2l;
		item.setId(testLong);
		assertEquals(testLong, item.getId());
	}

	@Test
	public void testItemNameSet()
	{
		String testLong = "bah";
		item.setItemName(testLong);
		assertEquals(testLong, item.getItemName());
	}

	@Test
	public void testItemValueSet()
	{
		Long testLong = 2l;
		item.setItemValue(testLong);
		assertEquals(testLong, item.getItemValue());
	}

}
