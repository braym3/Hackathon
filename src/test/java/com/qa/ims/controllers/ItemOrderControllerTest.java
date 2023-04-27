package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemOrderController;
import com.qa.ims.persistence.dao.ItemOrderDAO;
import com.qa.ims.persistence.domain.ItemOrder;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemOrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private ItemOrderDAO dao;

	@InjectMocks
	private ItemOrderController controller;

	@Test
	public void testCreate() {
		final Long itemId = 1L;
		final Long orderId = 1L;
		final Long orderQuant = 15098095L;
		final ItemOrder created = new ItemOrder(itemId, orderId, orderQuant);

		Mockito.when(utils.getLong()).thenReturn(itemId,orderId,orderQuant);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<ItemOrder> itemorders = new ArrayList<>();
		itemorders.add(new ItemOrder(1L, 1L, 1525L));

		Mockito.when(dao.readAll()).thenReturn(itemorders);

		assertEquals(itemorders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		ItemOrder updated = new ItemOrder(1L, 1L, 15098085105L);

		Mockito.when(this.utils.getLong()).thenReturn(1L, 1L, 15098085105L);
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(3)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long orderID = 1L;

		Mockito.when(utils.getLong()).thenReturn(orderID);
		Mockito.when(dao.delete(orderID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(orderID);
	}

}
