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

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Warehouse;
import com.qa.ims.utils.DBUtils;

public class WarehouseDAO implements Dao<Warehouse>{
	
	public static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Creates a warehouse object from a result set
	 * 
	 * @param resultSet - result set from the warehouse table
	 * 
	 * @return The Warehouse object modelled from the result set
	 */
	@Override
	public Warehouse modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		String postcode = resultSet.getString("postcode");
		return new Warehouse(id, name, postcode);
	}


	/**
	 * Reads all warehouses from the database
	 * 
	 * @return A list of warehouses
	 */
	@Override
	public List<Warehouse> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM warehouses");) {
			List<Warehouse> warehouses = new ArrayList<>();
			while (resultSet.next()) {
				warehouses.add(modelFromResultSet(resultSet));
			}
			return warehouses;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Warehouse readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM warehouses ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a warehouse in the database
	 * 
	 * @param warehouse - takes in a warehouse object
	 */
	@Override
	public Warehouse create(Warehouse warehouse) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO warehouses(name, postcode) VALUES (?, ?)");) {
			statement.setString(1, warehouse.getName());
			statement.setString(2, warehouse.getPostcode());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Warehouse read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM warehouses WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a warehouse in the database
	 * 
	 * @param warehouse - takes in a warehouse object, the id field will be used to
	 *                 update that warehouse in the database
	 * @return
	 */
	@Override
	public Warehouse update(Warehouse warehouse) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE warehouses SET name = ?, postcode = ? WHERE id = ?");) {
			statement.setString(1, warehouse.getName());
			statement.setString(2, warehouse.getPostcode());
			statement.setLong(3, warehouse.getId());
			statement.executeUpdate();
			return read(warehouse.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a warehouse in the database
	 * 
	 * @param id - id of the warehouse
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM warehouses WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
