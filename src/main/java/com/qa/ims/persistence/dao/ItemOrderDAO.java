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

import com.qa.ims.persistence.domain.ItemOrder;
import com.qa.ims.utils.DBUtils;

public class ItemOrderDAO implements Dao<ItemOrder> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public ItemOrder modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemId = resultSet.getLong("item_id");
		Long orderId = resultSet.getLong("order_id");
		Long orderQuant = resultSet.getLong("orderquant");

		return new ItemOrder(itemId, orderId, orderQuant);
	}

	/**
	 * Reads all order from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<ItemOrder> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM itemorders");) {
			List<ItemOrder> itemOrders = new ArrayList<>();
			while (resultSet.next()) {
				itemOrders.add(modelFromResultSet(resultSet));
			}
			return itemOrders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	/*Returns the latest */

	public ItemOrder readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM itemorders ORDER BY item_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a order in the database
	 * 
	 * @param itemOrder - takes in a order object. id will be ignored
	 */
	@Override
	public ItemOrder create(ItemOrder itemOrder) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO itemorders(item_id, order_id, orderquant) VALUES (?, ?, ?)");) {
			statement.setLong(1, itemOrder.getItemId());
			statement.setLong(2, itemOrder.getOrderId());
			statement.setLong(3, itemOrder.getOrderQuant());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	//Displays item orders based on order id as it's more logical to see what's in an order vs all instances of item

	@Override
	public ItemOrder read(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM itemorders WHERE order_id = ?");) {
			statement.setLong(1, orderId);
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

	public List<ItemOrder> readOrderId(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM itemorders WHERE order_id = ?");) {
			statement.setLong(1, orderId);
			List<ItemOrder> itemOrders = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					itemOrders.add(modelFromResultSet(resultSet));
				}
				return itemOrders;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a order in the database
	 * 
	 * @param order - takes in an order object, the id field will be used to
	 *                 update that order in the database
	 * @return
	 */
	@Override
	public ItemOrder update(ItemOrder order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE itemorders SET orderquant = ? WHERE order_id = ? AND item_id = ?");) {
			statement.setLong(1, order.getOrderQuant());
			statement.setLong(2, order.getOrderId());
			statement.setLong(3, order.getItemId());
			statement.executeUpdate();
			return read(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes an customer in the database
	 * 
	 * @param id - order of the customer
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM itemorders WHERE order_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
