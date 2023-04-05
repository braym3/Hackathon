package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.ItemOrderDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.ItemOrder;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@Mock
	private ItemOrderDAO itemOrderDAO;

	@Mock
	private ItemDAO itemDAO;

	@InjectMocks
	private OrderController controller;


	@Test
	public void testCreate() {
		final String orderName = "barry";
		final Long customerID = 1L;
		final Order created = new Order(orderName, customerID);

		Mockito.when(utils.getString()).thenReturn(orderName);
		Mockito.when(utils.getLong()).thenReturn(customerID);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, "barry", 1L));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Order updated = new Order(1L, "barryJ", 1L);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getOrderName());
		Mockito.when(this.utils.getLong()).thenReturn(updated.getCustomerId());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(2 )).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}


	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

	@Test
	public void testCalculation() {
		final long ID = 1L;

		final List<ItemOrder> itemOrderList = new ArrayList<ItemOrder>( List.of(new ItemOrder(1L, 1L, 23L)));
		System.out.println(itemOrderList);
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(itemOrderDAO.readOrderId(ID)).thenReturn(itemOrderList);
		Mockito.when(itemDAO.read(1L)).thenReturn(new Item(1L, "temp", 1L));

		assertEquals(23L, this.controller.calculate());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(itemOrderDAO, Mockito.times(1)).readOrderId(ID);
		Mockito.verify(itemDAO, Mockito.times(1)).read(1L);

	}

}
