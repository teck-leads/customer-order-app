package com.techleads.app.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
	private Integer customerId;
	private String customerName;
	private LocalDate orderDate;
	private Address address;
	private List<Orders> orders;
	private TotalAmount totalAmount;

}
