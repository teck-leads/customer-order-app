package com.techleads.app.repository.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.techleads.app.model.Customer;

public class ExtractCustomerById implements ResultSetExtractor<Customer> {
	Customer customer = new Customer();

	@Override
	public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
		while (rs.next()) {
			customer.setCustomerId(rs.getInt("CUST_ID"));
			customer.setCustomerName(rs.getString("CUST_NAME"));
			String stringDate = rs.getString("ORDER_DTE");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(stringDate, formatter);
			customer.setOrderDate(date);
			customer.setWeekend(rs.getString("WEEKEND"));

		}
		return customer;
	}

}
