package com.techleads.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.techleads.app.model.Address;
import com.techleads.app.model.Customer;
import com.techleads.app.model.Orders;
import com.techleads.app.model.TotalAmount;

public class ExtractAllCustomerById implements ResultSetExtractor<Customer> {
	Customer customer = new Customer();

	@Override
	public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Orders> orders = new ArrayList<>();
		while (rs.next()) {
			customer.setCustomerId(rs.getInt("CUST_ID"));
			customer.setCustomerName(rs.getString("CUST_NAME"));
			String stringDate = rs.getString("ORDER_DTE");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(stringDate, formatter);
			customer.setOrderDate(date);
			customer.setWeekend(rs.getString("WEEKEND"));

			Address addrs =	new Address();
			if (null != addrs) {
				addrs.setAddrId(rs.getInt("ID_ADDRS"));
				addrs.setStreetName(rs.getString("STREETNAME"));
				addrs.setCity(rs.getString("CITY"));
				addrs.setState(rs.getString("STATE"));
				addrs.setMobileNum(rs.getString("MOBILENUM"));
				addrs.setPinCode(rs.getString("PINCODE"));
				addrs.setCustomerId(rs.getLong("ADDR_CUST_ID"));

				customer.setAddress(addrs);
			}

		
			if (null != orders) {
				Orders order = new Orders();
				order.setItemId(rs.getInt("ITEM_ID"));
				order.setItemType(rs.getString("ITEM_TYP"));
				order.setItemName(rs.getString("ITEM_NME"));
				order.setQuantity(rs.getInt("QUANTITY"));
				order.setPrice(rs.getDouble("PRICE"));
				order.setDiscount(rs.getDouble("DISCOUNT"));
				order.setTotalAmount(rs.getDouble("TOTALAMOUNT"));
				order.setCustomerId(rs.getLong("ORD_CUST_ID"));

				orders.add(order);
				customer.setOrders(orders);
			}

			TotalAmount totalAmount = new TotalAmount();
			if (null != totalAmount) {
				totalAmount.setTmtId(rs.getInt("TMT_ID"));
				totalAmount.setItemTotal(rs.getDouble("ITEM_TOTAL"));
				totalAmount.setShippingCharge(rs.getDouble("SHIPPING_CHARGE"));
				totalAmount.setOrderTotal(rs.getDouble("ORDER_TOTAL"));
				totalAmount.setCustomerId(rs.getLong("TOT_CUST_ID"));
				customer.setTotalAmount(totalAmount);
			}

		}
		return customer;
	}

}
