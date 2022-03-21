package com.cognizant.portal.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subscription {

	
	private int subscriptionId;
	
	private int memberId;
	
	private Date subscriptionDate;
	
	private String refillOccurence;
	
	private String memberLocation;



}
