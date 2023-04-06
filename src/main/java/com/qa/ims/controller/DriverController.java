package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.DriverDAO;
import com.qa.ims.persistence.domain.Driver;
import com.qa.ims.utils.Utils;

public class DriverController implements CrudController<Driver>{
	public static final Logger LOGGER = LogManager.getLogger();

	private DriverDAO driverDAO;
	private Utils utils;

	public DriverController(DriverDAO driverDAO, Utils utils) {
		super();
		this.driverDAO = driverDAO;
		this.utils = utils;
	}

	/**
	 * Reads all drivers to the logger
	 */
	@Override
	public List<Driver> readAll() {
		List<Driver> drivers = driverDAO.readAll();
		for (Driver driver : drivers) {
			LOGGER.info(driver);
		}
		return drivers;
	}
	
	/**
	 * Reads driver belonging to id to the logger and returns the driver object
	 * 
	 * 
	 */
	public Driver read() {
		LOGGER.info("Please enter a driver ID");
		Long id = utils.getLong();
		Driver driver;
		try {
			driver = driverDAO.read(id);
			return driver;
		} catch (Exception e) {
			LOGGER.info("No driver found with ID: " + id + ". Please try again");
		}
		return null;
	}
	
	/**
	 * Reads all drivers with no assigned orders to the logger
	 */
	public List<Driver> readUnassignedDrivers(){
		List<Driver> unassignedDrivers = driverDAO.readAll();
		for (Driver driver : unassignedDrivers) {
			LOGGER.info(driver);
		}
		return unassignedDrivers;
	}
	
	

	/**
	 * Creates a driver by taking in user input
	 */
	@Override
	public Driver create() {
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		LOGGER.info("Please enter a warehouse ID");
		Long warehouseID = utils.getLong();
		Driver driver = driverDAO.create(new Driver(firstName, surname, warehouseID));
		LOGGER.info("Driver created");
		return driver;
	}

	/**
	 * Updates an existing driver by taking in user input
	 */
	@Override
	public Driver update() {
		LOGGER.info("Please enter the id of the driver you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		LOGGER.info("Please enter a warehouse ID");
		Long warehouseID = utils.getLong();
		Driver driver = driverDAO.update(new Driver(id, firstName, surname, warehouseID));
		LOGGER.info("Driver Updated");
		return driver;
	}

	/**
	 * Deletes an existing driver by the id of the driver
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the driver you would like to delete");
		Long id = utils.getLong();
		return driverDAO.delete(id);
	}
}
