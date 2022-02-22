package com.techleads.app.repository.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.techleads.app.model.TotalAmount;

public class ExtractTotalAmountByCustId implements ResultSetExtractor<TotalAmount> {
	TotalAmount totalAmount = new TotalAmount();

	@Override
	public TotalAmount extractData(ResultSet rs) throws SQLException, DataAccessException {
		while (rs.next()) {
			totalAmount.setTmtId(rs.getInt("TMT_ID"));
			totalAmount.setItemTotal(rs.getDouble("ITEM_TOTAL"));
			totalAmount.setShippingCharge(rs.getDouble("SHIPPING_CHARGE"));
			totalAmount.setOrderTotal(rs.getDouble("ORDER_TOTAL"));
			totalAmount.setCustomerId(rs.getLong("CUST_ID"));
		}

		return totalAmount;
	}

}
