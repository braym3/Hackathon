package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum Sections {

	DRIVERS("Information for drivers"), MANAGERS("Information for Managers"), ADMIN("Information about Orders and Items"),
	STOP("To close the application");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private Sections(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	public static void printDomains() {
		for (Sections domain : Sections.values()) {
			LOGGER.info(domain.getDescription());
		}
	}

	public static Sections getDomain(Utils utils) {
		Sections domain;
		while (true) {
			try {
				domain = Sections.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}

}
