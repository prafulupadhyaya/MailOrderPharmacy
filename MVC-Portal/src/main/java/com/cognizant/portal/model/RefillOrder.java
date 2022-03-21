package com.cognizant.portal.model;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefillOrder {
	
	int refillOrderId;
	
	int subscriptionId;
	
	Date refillDate;	
	Date refillDateNext;
	boolean status;
	boolean visited;
	List<RefillOrderLine> refillOrderLine;
	
}
