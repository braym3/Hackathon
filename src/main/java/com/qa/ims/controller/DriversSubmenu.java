package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum DriversSubmenu {

	GETDELIVERIES("Find your deliveries"), UNDELIVERED("Find Undelivered packages"), MARKCOMPLETE("Mark a Delivery as complete"),
	RETURN("To return to main menu");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private DriversSubmenu(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	public static void printDomains() {
		for (DriversSubmenu domain : DriversSubmenu.values()) {
			LOGGER.info(domain.getDescription());
		}
	}

	public static DriversSubmenu getDomain(Utils utils) {
		DriversSubmenu domain;
		while (true) {
			try {
				domain = DriversSubmenu.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}

}
