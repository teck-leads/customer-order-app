package com.techleads.app.repository.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.techleads.app.model.Orders;

public class ExtractAllOrdersByCustId implements ResultSetExtractor<List<Orders>> {

	List<Orders> orders = new ArrayList<>();

	@Override
	public List<Orders> extractData(ResultSet rs) throws SQLException, DataAccessException {
		while (rs.next()) {
			Orders order = new Orders();
			order.setItemId(rs.getInt("ITEM_ID"));
			order.setItemType(rs.getString("ITEM_TYP"));
			order.setItemName(rs.getString("ITEM_NME"));
			order.setQuantity(rs.getInt("QUANTITY"));
			order.setPrice(rs.getDouble("PRICE"));
			order.setDiscount(rs.getDouble("DISCOUNT"));
			order.setTotalAmount(rs.getDouble("TOTALAMOUNT"));
			order.setCustomerId(rs.getLong("CUST_ID"));

			orders.add(order);
		}
		return orders;
	}

}
