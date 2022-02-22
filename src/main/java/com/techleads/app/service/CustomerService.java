package com.techleads.app.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techleads.app.model.Customer;
import com.techleads.app.model.Orders;
import com.techleads.app.model.TotalAmount;
import com.techleads.app.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer saveCustomerAddress(Customer customer) {
		applyDiscount(customer);
		customer = customerRepository.saveCustomerAddress(customer);
		return customer;
	}

	public Customer findCustomerById(Integer id) {
		Customer findById = customerRepository.findCustomerById(id);
		return findById;
	}

	public void applyDiscount(Customer customer) {
		LocalDate orderDate = customer.getOrderDate();
		boolean isWeekend = isWeekend(orderDate);
		List<Orders> orders = customer.getOrders();
		if (isWeekend) {
			// apply 5% discount on weekends

			for (Orders od : orders) {
				// TODO if Item type is book add 5% additional discount
				if (od.getItemType().equalsIgnoreCase("Book")) {
					double applyDiscount = applyDiscount(od.getPrice(), 10);
					od.setDiscount(applyDiscount * od.getQuantity());
					od.setTotalAmount(((od.getPrice() * od.getQuantity()) - (applyDiscount * od.getQuantity())));
					// TODO if Item type is Grocery add 1% additional discount
				} else if (od.getItemType().equalsIgnoreCase("Grocery")) {
					double applyDiscount = applyDiscount(od.getPrice(), 6);
					od.setDiscount(applyDiscount * od.getQuantity());
					od.setTotalAmount(((od.getPrice() * od.getQuantity()) - (applyDiscount * od.getQuantity())));
				}

			}

		} else {
			// apply 2% discount on weekdays
			for (Orders od : orders) {
				// TODO if Item type is book add 5% additional discount
				if (od.getItemType().equalsIgnoreCase("Book")) {
					double applyDiscount = applyDiscount(od.getPrice(), 7);
					od.setDiscount(applyDiscount * od.getQuantity());
					od.setTotalAmount(((od.getPrice() * od.getQuantity()) - (applyDiscount * od.getQuantity())));
					// TODO if Item type is Grocery add 1% additional discount
				} else if (od.getItemType().equalsIgnoreCase("Grocery")) {
					double applyDiscount = applyDiscount(od.getPrice(), 3);
					od.setDiscount(applyDiscount * od.getQuantity());
					od.setTotalAmount(((od.getPrice() * od.getQuantity()) - (applyDiscount * od.getQuantity())));
				}

			}

		}
		double totalItemAmt = orders.stream().mapToDouble(Orders::getTotalAmount).sum();
		TotalAmount tmt = new TotalAmount();
		tmt.setItemTotal(totalItemAmt);

		if (totalItemAmt < 1000) {
			tmt.setShippingCharge(100.00);
			tmt.setOrderTotal(totalItemAmt + tmt.getShippingCharge());
		} else {
			tmt.setShippingCharge(0.00);
			tmt.setOrderTotal(totalItemAmt + tmt.getShippingCharge());
		}
		customer.setOrders(orders);
		customer.setTotalAmount(tmt);
	}

	public static boolean isWeekend(final LocalDate ld) {
		DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
		return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
	}

	public static double applyDiscount(double price, double discount) {
		return (price * ((double) discount / 100));
	}
}
