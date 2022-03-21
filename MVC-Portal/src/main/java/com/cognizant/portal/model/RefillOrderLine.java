package com.cognizant.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefillOrderLine {
	
	int id;
	private String drug;
	private int drugQuantity;
	
	
}
