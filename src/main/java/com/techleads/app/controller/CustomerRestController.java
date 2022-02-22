package com.techleads.app.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techleads.app.model.Address;
import com.techleads.app.model.Customer;
import com.techleads.app.model.Orders;
import com.techleads.app.model.TotalAmount;
import com.techleads.app.service.CustomerService;

@RestController
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;

	@GetMapping(value = { "/customers" })
	public Customer findAllCustomers() {

		Address ad = new Address();
		ad.setCity("Hyderabad");
		ad.setMobileNum("8019772039");
		ad.setPinCode("500018");
		ad.setState("Telangana");
		ad.setStreetName("V.V Nagar");
		Customer cs = new Customer();
		cs.setCustomerId(12456);
		cs.setCustomerName("Madhav");
		
		
		 
		
		    Orders o1=new Orders();
		    o1.setItemId(31);
		    o1.setItemType("Grocery");
		    o1.setItemName("ABC Surf");
		    o1.setQuantity(1);
		    o1.setPrice(500.00);
		    o1.setDiscount(35.00);
		    o1.setTotalAmount(465.00);
		    
		    Orders o2=new Orders();
		    o2.setItemId(25);
		    o2.setItemType("Book");
		    o2.setItemName("Zero to One, Peter Thiel");
		    o2.setQuantity(1);
		    o2.setPrice(30.00);
		    o2.setDiscount(00.09);
		    o2.setTotalAmount(29.1);
		    List<Orders> ords=new ArrayList<>();
		    ords.add(o2);
		    ords.add(o1);
		    
		    TotalAmount tmt=new TotalAmount();
		    tmt.setItemTotal(494.1);
		    tmt.setOrderTotal(594.1);
		    tmt.setShippingCharge(100.00);
		    cs.setTotalAmount(tmt);
		    cs.setOrders(ords);
		    
		
		    
		LocalDate of = LocalDate.of(2021, Month.FEBRUARY, 24);
		cs.setOrderDate(of);
		cs.setAddress(ad);

		return cs;
	}
	
	@GetMapping(value = { "/customers/{id}" })
	public Customer findCustomerById(@PathVariable("id") Integer id) {
		Customer customer = customerService.findCustomerById(id);
		return customer;
	}
	
	@PostMapping(value = { "/customers" })
	public String saveOrder(@RequestBody Customer customer) {
		String saveCustomerAddress = customerService.saveCustomerAddress(customer);
		return saveCustomerAddress;
	}

}
