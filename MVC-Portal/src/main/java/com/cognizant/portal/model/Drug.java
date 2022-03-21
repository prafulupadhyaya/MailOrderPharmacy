package com.cognizant.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drug")
public class Drug{
	@Id
	@GeneratedValue
	int id;
	@Column(name="drug_name")
	String drugName;
	@Column(name="quantity")
	int quantity;
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Drug(String drugName, int quantity) {
		super();
		this.drugName = drugName;
		this.quantity = quantity;
	}
	public Drug() {
		super();
	}
	
}
