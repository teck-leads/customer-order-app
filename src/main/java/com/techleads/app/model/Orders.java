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
public class Orders {
	private Integer itemId;
	private String itemType;
	private String itemName;
	private Integer quantity;
	private Double price;
	private Double discount;
	private Double totalAmount;
	private Long customerId;

}
