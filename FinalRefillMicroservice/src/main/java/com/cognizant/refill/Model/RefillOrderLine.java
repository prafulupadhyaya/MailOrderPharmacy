package com.cognizant.refill.Model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="refill_order_line")
public class RefillOrderLine {
	public RefillOrderLine(String drug, int drugQuantity) {
		super();
		this.drug = drug;
		this.drugQuantity = drugQuantity;
	}
	@Id
	@GeneratedValue
	int id;
	@Column(name="drug")
	private String drug;
	@Column(name="drug_quantity")
	private int drugQuantity;
	
}
