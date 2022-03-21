package com.cognizant.portal.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {


	private int prescriptionId;
	
	private int memberId;
	
	private int insurancePolicyNumber;
	
	private String insuranceProvider;
	
	private Date prescriptionDate;
	
	private List<Drug> drugs;
	
	private int dosage;
	
	private String presCourse;
	
	private String doctorName;

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", memberId=" + memberId + ", insurancePolicyNumber="
				+ insurancePolicyNumber + ", insuranceProvider=" + insuranceProvider + ", prescriptionDate="
				+ prescriptionDate + ", drugs=" + drugs + ", dosage=" + dosage + ", presCourse=" + presCourse
				+ ", doctorName=" + doctorName + "]";
	}

	

}
