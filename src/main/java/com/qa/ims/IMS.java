package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.DriverController;
import com.qa.ims.controller.DriversSubmenu;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.controller.Sections;
import com.qa.ims.controller.WarehouseController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.ItemOrderController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.*;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class IMS {

	public static final Logger LOGGER = LogManager.getLogger();

	private final CustomerController customers;
	private final ItemController items;
	private final OrderController orders;
	private final ItemOrderController itemOrders;
	private final DriverController drivers;
	private final WarehouseController warehouses;
	private final Utils utils;

	public IMS() {
		this.utils = new Utils();
		final CustomerDAO custDAO = new CustomerDAO();
		final OrderDAO ordDAO = new OrderDAO();
		final ItemDAO itemDAO = new ItemDAO();
		final ItemOrderDAO itemOrderDAO = new ItemOrderDAO();
		final DriverDAO driversDAO = new DriverDAO();
		final WarehouseDAO warehouseDAO = new WarehouseDAO();

		this.customers = new CustomerController(custDAO, utils);
		this.items = new ItemController(itemDAO, utils);
		this.orders = new OrderController(ordDAO, utils, itemDAO, itemOrderDAO);
		this.itemOrders = new ItemOrderController(itemOrderDAO, utils);
		this.drivers = new DriverController(driversDAO, utils);
		this.warehouses = new WarehouseController(warehouseDAO, utils);
	}
	public void sectionSystems() {
		LOGGER.info("Welcome to the Delivery Management System!");
		DBUtils.connect();

		Sections section = null;
		do {
			LOGGER.info("Which section would you like to use?");
			Sections.printDomains();

			section = Sections.getDomain(utils);

			sectionAction(section);

		} while (section != Sections.STOP);
	}
	private void sectionAction(Sections section) {
		boolean changeSection = false;
		Domain domain = null;
		DriversSubmenu driversSubmenu = null;
		do {

			switch (section) {
			case DRIVERS:
				driversSubmenu = driverSystem(driversSubmenu);
				return;
			case MANAGERS:
				LOGGER.info("I'm a Manager");
				return;
			case ADMIN:
				domain = imsSystem(domain);
				break;
			case STOP:
				return;
			default:
				break;
			}

			System.out.println(domain);

			
		// 	Action.printActions(active);
		// 	Action action = Action.getAction(utils);

			if (domain == Domain.STOP) {
				changeSection = true;
			}
			if (driversSubmenu == DriversSubmenu.RETURN) {
				changeSection = true;
			}
		} while (!changeSection);
	}

	public DriversSubmenu driverSystem(DriversSubmenu driversSubmenu) {
		LOGGER.info("Welcome to the Driver Management System!");

		do {
			LOGGER.info("What would you like to do?");
			DriversSubmenu.printDomains();

			driversSubmenu = DriversSubmenu.getDomain(utils);

			driversSubmenu = driverAction(driversSubmenu);


		} while (driversSubmenu != DriversSubmenu.RETURN);
		return driversSubmenu;
	}

	public DriversSubmenu driverAction(DriversSubmenu driversSubmenu) {
		boolean changeDriverSubmenu = false;
		do {

			switch (driversSubmenu) {
			case GETDELIVERIES:
				drivers.readDriverOrders();
				break;
			case UNDELIVERED:
				drivers.readUndeliveredOrders();
				break;
			case MARKCOMPLETE:
			LOGGER.info("Mark Complete woo!");
				break;
			case RETURN:
				break;
			default:
				break;
			}

			if (driversSubmenu == DriversSubmenu.RETURN) {
				changeDriverSubmenu = true;
			}
			else{
				LOGGER.info("What would you like to do?");
				DriversSubmenu.printDomains();

				driversSubmenu = DriversSubmenu.getDomain(utils);

				driversSubmenu = driverAction(driversSubmenu);
			}
		} while (!changeDriverSubmenu);
		return driversSubmenu;
	}





		
	public Domain imsSystem(Domain domain) {
		LOGGER.info("Welcome to the Item Management System!");

		do {
			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains();

			domain = Domain.getDomain(utils);

			domainAction(domain);

		} while (domain != Domain.STOP);
		return domain;
	}

	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?> active = null;
			switch (domain) {
			case CUSTOMER:
				active = this.customers;
				break;
			case ITEM:
				active = this.items;
				break;
			case ORDER:
				active = this.orders;
				break;
			case ITEMORDER:
				active = this.itemOrders;
				break;
			case DRIVERS:
				active = this.drivers;
				break;
			case WAREHOUSE:
				active = this.warehouses;
				break;
			case STOP:
				return;
			default:
				break;
			}

			LOGGER.info(() ->"What would you like to do with " + domain.name().toLowerCase() + ":");

			Action.printActions(active);
			Action action = Action.getAction(utils);

			if (action == Action.RETURN) {
				changeDomain = true;
			} else {
				doAction(active, action);
			}
		} while (!changeDomain);
	}

	
	public void doAction(CrudController<?> crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case CALCULATE:
			if(crudController.getClass().equals(OrderController.class))
			{
				((OrderController)crudController).calculate();
			}
			else
			{
				break;
			}
		case RETURN:
			break;
		default:
			break;
		}
	}

}
