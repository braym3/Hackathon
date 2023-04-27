package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.WarehouseDAO;
import com.qa.ims.persistence.domain.Warehouse;
import com.qa.ims.utils.Utils;

public class WarehouseController implements CrudController<Warehouse>{

	public static final Logger LOGGER = LogManager.getLogger();

	private WarehouseDAO warehouseDAO;
	private Utils utils;

	public WarehouseController(WarehouseDAO warehouseDAO, Utils utils) {
		super();
		this.warehouseDAO = warehouseDAO;
		this.utils = utils;
	}

	/**
	 * Reads all warehouses to the logger
	 */
	@Override
	public List<Warehouse> readAll() {
		List<Warehouse> warehouses = warehouseDAO.readAll();
		for (Warehouse warehouse : warehouses) {
			LOGGER.info(warehouse);
		}
		return warehouses;
	}

	/**
	 * Creates a warehouse by taking in user input
	 */
	@Override
	public Warehouse create() {
		LOGGER.info("Please enter the warehouse name");
		String name = utils.getString();
		LOGGER.info("Please enter the postcode of the warehouse");
		String postcode = utils.getString();
		Warehouse warehouse = warehouseDAO.create(new Warehouse(name, postcode));
		LOGGER.info("Warehouse created");
		return warehouse;
	}

	/**
	 * Updates an existing warehouse by taking in user input
	 */
	@Override
	public Warehouse update() {
		LOGGER.info("Please enter the id of the warehouse you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the warehouse name");
		String name = utils.getString();
		LOGGER.info("Please enter the postcode of the warehouse");
		String postcode = utils.getString();
		Warehouse warehouse = warehouseDAO.update(new Warehouse(id, name, postcode));
		LOGGER.info("Warehouse Updated");
		return warehouse;
	}

	/**
	 * Deletes an existing warehouse by the id of the warehouse
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the warehouse you would like to delete");
		Long id = utils.getLong();
		return warehouseDAO.delete(id);
	}
}
