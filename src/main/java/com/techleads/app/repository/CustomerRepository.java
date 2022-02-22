package com.techleads.app.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.techleads.app.DBQueries;
import com.techleads.app.model.Address;
import com.techleads.app.model.Customer;
import com.techleads.app.model.Orders;
import com.techleads.app.model.TotalAmount;
import com.techleads.app.repository.extractors.ExtractAddressByCustId;
import com.techleads.app.repository.extractors.ExtractAllOrdersByCustId;
import com.techleads.app.repository.extractors.ExtractCustomerById;
import com.techleads.app.repository.extractors.ExtractTotalAmountByCustId;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CustomerRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PlatformTransactionManager platformTransactionManager;

	public String saveCustomerAddress(Customer customer) {

		DefaultTransactionDefinition paramTransactionDefinition = new DefaultTransactionDefinition();

		TransactionStatus status = platformTransactionManager.getTransaction(paramTransactionDefinition);
		try {
			// 1. Save customer
			long customerId = saveCustomer(customer);
			log.info("customer id {} ", customerId);
			// 2. save address
			Address address = customer.getAddress();
			address.setCustomerId(customerId);
			int saveAddress = saveAddress(address);
			log.info("address details {} ", saveAddress);
			// 3. save orders

			List<Orders> orders = customer.getOrders();
			orders.parallelStream().forEach(order -> order.setCustomerId(customerId));

			int[] ordersBatchInsert = ordersBatchInsert(orders);
			log.info("ordersBatchInsert details {} ", ordersBatchInsert);
			// Save total amount details
			TotalAmount totalAmount = customer.getTotalAmount();
			totalAmount.setCustomerId(customerId);
			saveTotalAmount(totalAmount);
			platformTransactionManager.commit(status);
			return "Customer is saved!";
		} catch (Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);
		}

		return "Warning not saved!";
	}

	public Customer findCustomerById(Integer id) {
		Customer customer = null;
		try {
			Object[] params = { id };
			customer = jdbcTemplate.query(DBQueries.SELECT_CUST_BY_ID, new ExtractCustomerById(), params);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		Address address = findAddrsByCustomerId(id);
		customer.setAddress(address);
		List<Orders> orders = findAllOrdersByCustomerId(id);
		customer.setOrders(orders);

		TotalAmount totalAmount = findTotalAmountByCustomerId(id);
		customer.setTotalAmount(totalAmount);

		return customer;

	}

	public List<Orders> findAllOrdersByCustomerId(Integer customerId) {
		Object[] params = { customerId };
		List<Orders> orders = jdbcTemplate.query(DBQueries.SELECT_ORDERS_BY_CUST_ID, new ExtractAllOrdersByCustId(),
				params);

		return orders;

	}

	public Address findAddrsByCustomerId(Integer customerId) {
		Address address = null;
		try {
			Object[] params = { customerId };
			address = jdbcTemplate.query(DBQueries.SELECT_ADDRS_BY_ID, new ExtractAddressByCustId(), params);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return address;

	}

	public TotalAmount findTotalAmountByCustomerId(Integer customerId) {
		TotalAmount totalAmount = null;
		try {
			Object[] params = { customerId };
			totalAmount = jdbcTemplate.query(DBQueries.SELECT_TOTAL_AMT_BY_CUST_ID, new ExtractTotalAmountByCustId(),
					params);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return totalAmount;

	}

	public long saveCustomer(Customer customer) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(connection -> {

				PreparedStatement ps = connection.prepareStatement(DBQueries.INSERT_INTO_CUSTOMER,
						new String[] { "CUST_ID" });
				ps.setString(1, customer.getCustomerName());
				Date date = Date.valueOf(customer.getOrderDate());
				ps.setDate(2, date);
				return ps;
			}, keyHolder);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		Number key = keyHolder.getKey();

		return key.longValue();
	}

	public int saveAddress(Address address) {
		int count = 0;
		try {
			Object[] params = { address.getStreetName(), address.getCity(), address.getState(), address.getMobileNum(),
					address.getPinCode(), address.getCustomerId() };
			count = jdbcTemplate.update(DBQueries.INSERT_INTO_ADDRESS, params);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int[] ordersBatchInsert(List<Orders> orders) {
		int[] batchUpdate = null;

		try {
			batchUpdate = jdbcTemplate.batchUpdate(DBQueries.INSERT_INTO_ORDERS, new BatchPreparedStatementSetter() {

				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, orders.get(i).getItemType());
					ps.setString(2, orders.get(i).getItemName());
					ps.setInt(3, orders.get(i).getQuantity());
					ps.setDouble(4, orders.get(i).getPrice());
					ps.setDouble(5, orders.get(i).getDiscount());
					ps.setDouble(6, orders.get(i).getTotalAmount());
					ps.setDouble(7, orders.get(i).getCustomerId());
				}

				public int getBatchSize() {
					return orders.size();
				}

			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return batchUpdate;
	}

	public int saveTotalAmount(TotalAmount totalAmount) {
		int count = 0;
		try {
			Object[] params = { totalAmount.getItemTotal(), totalAmount.getShippingCharge(),
					totalAmount.getOrderTotal(), totalAmount.getCustomerId() };
			count = jdbcTemplate.update(DBQueries.INSERT_INTO_TOT_AMT, params);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count;
	}

}
