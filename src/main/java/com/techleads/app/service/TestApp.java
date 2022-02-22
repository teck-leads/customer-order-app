package com.techleads.app.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import com.techleads.app.model.Address;
import com.techleads.app.model.Customer;
import com.techleads.app.model.Orders;
import com.techleads.app.model.TotalAmount;

public class TestApp {

	public static void main(final String[] args) {
		
		
//		Address ad = new Address();
//		ad.setCity("Hyderabad");
//		ad.setMobileNum("8019772039");
//		ad.setPinCode("500018");
//		ad.setState("Telangana");
//		ad.setStreetName("V.V Nagar");
//		Customer cs = new Customer();
//		cs.setCustomerId(12456);
//		cs.setCustomerName("Madhav");
//		
//		
//		 
//		
//		    Orders o1=new Orders();
//		    o1.setItemId(31);
//		    o1.setItemType("Grocery");
//		    o1.setItemName("ABC Surf");
//		    o1.setQuantity(1);
//		    o1.setPrice(500.00);
//		    o1.setDiscount(35.00);
//		    o1.setTotalAmount(465.00);
//		    
//		    Orders o2=new Orders();
//		    o2.setItemId(25);
//		    o2.setItemType("Book");
//		    o2.setItemName("Zero to One, Peter Thiel");
//		    o2.setQuantity(1);
//		    o2.setPrice(30.00);
//		    o2.setDiscount(00.09);
//		    o2.setTotalAmount(29.1);
//		    List<Orders> ords=new ArrayList<>();
//		    ords.add(o2);
//		    ords.add(o1);
//		    
//		    TotalAmount tmt=new TotalAmount();
//		    tmt.setItemTotal(494.1);
//		    tmt.setOrderTotal(594.1);
//		    tmt.setShippingCharge(100.00);
//		    cs.setTotalAmount(tmt);
//		    cs.setOrders(ords);
//		    
//		    
//		    ords.parallelStream()
//		    .forEach(or-> or.setCustomerId(123L));
//		    ords.forEach(System.out::println);
//		    
//		    System.exit(0);
//		    
//		
//		
//		
//		
//		double price =500.00;
//		double discount=7;
//		double applyDiscount = applyDiscount(price, discount);
//		System.out.println(applyDiscount);
//		
//		System.exit(0);
		LocalDate today = LocalDate.now();
		System.out.println("Is weekend : " + isWeekend(today));

		LocalDate someDate = LocalDate.of(2022, Month.FEBRUARY, 20); // 2nd-Jan-2021

		System.out.println("Is weekend : " + isWeekend(someDate));
		LocalDate someDate1 = LocalDate.of(2022, Month.FEBRUARY, 21);
		System.out.println("Is weekend : " + isWeekend(someDate1));
	}

	public static boolean isWeekend(final LocalDate ld) {
		DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
		return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
	}
	
	
	public static double applyDiscount(double price, double discount) {
		return (price*((double)discount/100));
	}

}
