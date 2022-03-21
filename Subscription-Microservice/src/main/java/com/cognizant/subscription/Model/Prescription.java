package com.cognizant.subscription.Model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="prescription")
public class Prescription {

	@Id
	@GeneratedValue
	@Column(name="prescription_id")	
	private int prescriptionId;
	
//no member id 
	@Column(name="ins_policy_number")
	private int insurancePolicyNumber;
	
	
	@Column(name="ins_provider")
	//Insurance Provider details
	private String insuranceProvider;
	
	
	@Column(name="prescription_date")
	private Date prescriptionDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Drug> drugs;
	
	@Column(name="dosage")
	private int dosage;
	
	@Column(name="pres_course")
	private String presCourse;
	
	@Column(name="doctor_name")
	private String doctorName;

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public int getInsurancePolicyNumber() {
		return insurancePolicyNumber;
	}

	public void setInsurancePolicyNumber(int insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
	}

	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	public Date getPrescriptionDate() {
		return prescriptionDate;
	}

	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public String getPresCourse() {
		return presCourse;
	}

	public void setPresCourse(String presCourse) {
		this.presCourse = presCourse;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", insurancePolicyNumber=" + insurancePolicyNumber
				+ ", insuranceProvider=" + insuranceProvider + ", prescriptionDate=" + prescriptionDate + ", drugs="
				+ drugs + ", dosage=" + dosage + ", presCourse=" + presCourse + ", doctorName=" + doctorName + "]";
	}
	


}