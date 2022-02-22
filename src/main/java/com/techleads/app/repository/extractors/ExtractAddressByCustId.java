package com.techleads.app.repository.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.techleads.app.model.Address;

public class ExtractAddressByCustId implements ResultSetExtractor<Address> {

	Address addrs = new Address();

	@Override
	public Address extractData(ResultSet rs) throws SQLException, DataAccessException {
		while (rs.next()) {
			addrs.setAddrId(rs.getInt("ID_ADDRS"));
			addrs.setStreetName(rs.getString("STREETNAME"));
			addrs.setCity(rs.getString("CITY"));
			addrs.setState(rs.getString("STATE"));
			addrs.setMobileNum(rs.getString("MOBILENUM"));
			addrs.setPinCode(rs.getString("PINCODE"));
			addrs.setCustomerId(rs.getLong("CUST_ID"));

		}
		return addrs;
	}

}
