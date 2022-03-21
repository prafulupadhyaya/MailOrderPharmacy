package com.cognizant.portal.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drugs {

	private int id;

	private String name;
	private String manufacturer;
	private Date manufacturedDate;
	private Date expiryDate;
	private int unitsPackage;
	private Double cost;
	private List<DrugDetails> drugDetails;


}
