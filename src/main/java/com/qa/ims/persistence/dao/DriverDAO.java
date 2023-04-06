package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Driver;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class DriverDAO implements Dao<Driver>{
	
	public static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Creates a driver object, reads driver to get list of driver's assigned orders
	 * 
	 * @param resultSet - result set from the driver table
	 * 
	 * @return The Driver object modelled from the result set
	 */
	@Override
	public Driver modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");

		return this.read(id);
	}
	
	/**
	 * Creates a driver object
	 * 
	 * @param driverRS - result set from the drivers table
	 * @param ordersRS  - result set from the orders table - all orders assigned to that driver id
	 * @return The Driver object modelled from the result sets
	 */
	public Driver modelFromResultSet(ResultSet driverRS, ResultSet ordersRS) throws SQLException {
		Long id = driverRS.getLong("id");
		String firstName = driverRS.getString("first_name");
		String surname = driverRS.getString("surname");
		Long driverWarehouseId = driverRS.getLong("warehouse_id");
		ArrayList<Order> orders = new ArrayList<Order>();
		Long orderId;
		Long customerId;
		Long delivered;
		Long warehouseId;
		while (ordersRS.next()) {
			orderId = ordersRS.getLong("id");
			customerId = ordersRS.getLong("customer_id");
			delivered = ordersRS.getLong("delivered");
			warehouseId = ordersRS.getLong("warehouse_id");
			orders.add(new Order(orderId, customerId, id, delivered, warehouseId));
		}
		return new Driver(id, firstName, surname, driverWarehouseId, orders);
	}


	/**
	 * Reads all drivers from the database
	 * 
	 * @return A list of drivers
	 */
	@Override
	public List<Driver> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM drivers");) {
			List<Driver> drivers = new ArrayList<>();
			while (resultSet.next()) {
				drivers.add(modelFromResultSet(resultSet));
			}
			return drivers;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Driver readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM drivers ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a driver in the database
	 * 
	 * @param driver - takes in a driver object. id will be ignored
	 */
	@Override
	public Driver create(Driver driver) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO drivers(first_name, surname, warehouse_id) VALUES (?, ?, ?)");) {
			statement.setString(1, driver.getFirstName());
			statement.setString(2, driver.getSurname());
			statement.setLong(3, driver.getWarehouseID());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Reads the driver in the database with the given id
	 * 
	 * @param id - takes in a driver id
	 */
	@Override
	public Driver read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM drivers WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet driverResultSet = statement.executeQuery();) {
				driverResultSet.next();
				// Use the driver ID to find all orders assigned to that driver
				PreparedStatement ordersStatement = connection.prepareStatement(
						"SELECT orders.id, orders.customer_id, orders.delivered, orders.warehouse_id FROM orders JOIN drivers ON orders.driver_id = drivers.id WHERE orders.driver_id = ?");
				ordersStatement.setLong(1, id);
				ResultSet ordersResultSet = ordersStatement.executeQuery();
				return modelFromResultSet(driverResultSet, ordersResultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Reads all drivers from the database with no assigned orders
	 * 
	 */
	public List<Driver> readUnassignedDrivers() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT drivers.id FROM drivers LEFT JOIN orders ON drivers.id = orders.driver_id WHERE orders.id IS NULL;");) {
			List<Driver> drivers = new ArrayList<>();
			while (resultSet.next()) {
				drivers.add(modelFromResultSet(resultSet));
			}
			return drivers;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	/**
	 * Reads all drivers from the database with the corresponding warehouse id
	 * 
	 */
	public List<Driver> readWarehouseDrivers(Long warehouseId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM drivers WHERE warehouse_id = ?");) {
			statement.setLong(1, warehouseId);
			List<Driver> drivers = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					drivers.add(modelFromResultSet(resultSet));
				}
				return drivers;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
			return new ArrayList<>();
	}

	
	

	/**
	 * Updates a driver in the database
	 * 
	 * @param driver - takes in a driver object, the id field will be used to
	 *                 update that driver in the database
	 * @return
	 */
	@Override
	public Driver update(Driver driver) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE drivers SET first_name = ?, surname = ?, warehouse_id = ? WHERE id = ?");) {
			statement.setString(1, driver.getFirstName());
			statement.setString(2, driver.getSurname());
			statement.setLong(3, driver.getWarehouseID());
			statement.setLong(4, driver.getId());
			statement.executeUpdate();
			return read(driver.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a driver in the database
	 * 
	 * @param id - id of the driver
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM drivers WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
