package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum ManagerSubmenu {

	ASSIGNDELIVERY("Assign delivery to driver"), WAREHOUSE("List all drivers at a warehouse"), 
	NODELIVERY("List all drivers with no assigned deliveries"),	NODRIVER("List all deliveries with no assigned drivers"),
	RETURN("To return to main menu");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private ManagerSubmenu(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	public static void printDomains() {
		for (ManagerSubmenu domain : ManagerSubmenu.values()) {
			LOGGER.info(domain.getDescription());
		}
	}

	public static ManagerSubmenu getDomain(Utils utils) {
		ManagerSubmenu domain;
		while (true) {
			try {
				domain = ManagerSubmenu.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}

}
