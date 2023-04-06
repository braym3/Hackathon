package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.ItemOrderDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.ItemOrder;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private ItemOrderDAO itemOrderDAO;
	private ItemDAO itemDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils, ItemDAO itemDAO, ItemOrderDAO itemOrderDAO) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		this.itemOrderDAO = itemOrderDAO;
		this.itemDAO = itemDAO;
	}

	/**
	 * Reads all order to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}
	
	/**
	 * Reads all orders with no assigned drivers from the given warehouse id to the logger
	 */
	public List<Order> readUnassigned() {
		LOGGER.info("Please enter a warehouse ID");
		Long id = utils.getLong();
		List<Order> orders = orderDAO.readUnassigned(id);
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}
	

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customerID");
		Long customerId = utils.getLong();
		LOGGER.info("Please enter a driverID");
		Long driverId = utils.getLong();
		LOGGER.info("Please enter a delivered state");
		Long delivered = utils.getLong();
		LOGGER.info("Please enter a warehouseID");
		Long warehouseId = utils.getLong();
		Order order = orderDAO.create(new Order(customerId,driverId,delivered,warehouseId));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a customerID");
		Long customerId = utils.getLong();
		LOGGER.info("Please enter a driverID");
		Long driverId = utils.getLong();
		LOGGER.info("Please enter a delivered state");
		Long delivered = utils.getLong();
		LOGGER.info("Please enter a warehouseID");
		Long warehouseId = utils.getLong();
		Order order = orderDAO.update(new Order(id, customerId,driverId,delivered,warehouseId));
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

	public int calculate()
	{
		LOGGER.info("Please enter the id of the order you would like to calculate");
		Long id = utils.getLong();
		Long total = 0L;
		List<ItemOrder> itemOrderList = itemOrderDAO.readOrderId(id);

		for (ItemOrder i : itemOrderList)
		{
			total += itemDAO.read(i.getItemId()).getItemValue() * i.getOrderQuant();
		}
		LOGGER.info("Total cost of Order " + id + " is " + total);
		return total.intValue();

	}

}
