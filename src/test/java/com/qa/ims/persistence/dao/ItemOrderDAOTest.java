package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.ItemOrder;
import com.qa.ims.utils.DBUtils;

public class ItemOrderDAOTest {

	private final ItemOrderDAO DAO = new ItemOrderDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final ItemOrder created = new ItemOrder(2L, 1L, 15980L);
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.create(new Item(2L, "temp", 2134L));
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<ItemOrder> expected = new ArrayList<>();
		expected.add(new ItemOrder(1L, 1L, 6590L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadOrderId() {
		List<ItemOrder> expected = new ArrayList<>();
		expected.add(new ItemOrder(1L, 1L, 6590L));
		assertEquals(expected, DAO.readOrderId(1L));
	}

	@Test
	public void testReadLatest() {
		assertEquals(new ItemOrder(1L, 1L, 6590L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new ItemOrder(1L, ID, 6590L), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final ItemOrder updated = new ItemOrder(1L, 1L, 2572895L);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}
