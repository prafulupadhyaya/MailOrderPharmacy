package com.cognizant.drugs.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data

@NoArgsConstructor
@Entity
@Table(name = "drug")
public class Drugs {
    

	@Id
      @GeneratedValue
      private int id;
	
	 @Column(name = "name")
	 private String name;
	 @Column(name = "manufacturer")
	 private String manufacturer;
	 @Column(name = "manufactured_date")
	 private Date manufacturedDate;
	 @Column(name = "expiry_date")
	 private Date expiryDate;
	 @Column(name = "units_package")
	 private int unitsPackage;
	 @Column(name = "cost")
	 private Double cost;
	 
	 @OneToMany(cascade = CascadeType.ALL)
	    private List<DrugDetails> drugDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Date getManufacturedDate() {
		return manufacturedDate;
	}

	public void setManufacturedDate(Date manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getUnitsPackage() {
		return unitsPackage;
	}

	public void setUnitsPackage(int unitsPackage) {
		this.unitsPackage = unitsPackage;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public List<DrugDetails> getDrugDetails() {
		return drugDetails;
	}

	public void setDrugDetails(List<DrugDetails> drugDetails) {
		this.drugDetails = drugDetails;
	}
	 
	 
	 
	 
}
