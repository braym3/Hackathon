package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemOrderDAO;
import com.qa.ims.persistence.domain.ItemOrder;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class ItemOrderController implements CrudController<ItemOrder> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemOrderDAO itemOrderDAO;
	private Utils utils;
	
	public ItemOrderController(ItemOrderDAO itemOrderDAO, Utils utils) {
		super();
		this.itemOrderDAO = itemOrderDAO;
		this.utils = utils;
	}


	/**
	 * Reads all order to the logger
	 */
	@Override
	public List<ItemOrder> readAll() {
		List<ItemOrder> itemOrders = itemOrderDAO.readAll();
		for (ItemOrder itemOrder : itemOrders) {
			LOGGER.info(itemOrder);
		}
		return itemOrders;
	}

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public ItemOrder create() {
		LOGGER.info("Please enter an Item ID");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter an Order ID");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter the quantity of Items");
		Long orderQuant = utils.getLong();
		ItemOrder itemOrder = itemOrderDAO.create(new ItemOrder(itemId, orderID,orderQuant ));
		LOGGER.info("ItemOrder created");
		return itemOrder;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public ItemOrder update() {
		LOGGER.info("Please enter the Item ID of the itemorder you wish to update");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter the Order ID of the itemorder you wish to update");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter the quantity of Items");
		Long orderQuant = utils.getLong();
		ItemOrder itemOrder = itemOrderDAO.update(new ItemOrder(itemId, orderID,orderQuant ));
		LOGGER.info("ItemOrder Updated");
		return itemOrder;
	}

	/**
	 * Deletes an existing order by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the Order id of the item order you would like to delete");
		Long id = utils.getLong();
		return itemOrderDAO.delete(id);
	}

}
