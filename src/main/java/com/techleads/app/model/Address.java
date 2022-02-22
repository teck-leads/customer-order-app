package com.techleads.app.model;

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
public class Address {
	private Integer addrId;
	private String streetName;
	private String city;
	private String state;
	private String mobileNum;
	private String pinCode;
	private Long customerId;

}
