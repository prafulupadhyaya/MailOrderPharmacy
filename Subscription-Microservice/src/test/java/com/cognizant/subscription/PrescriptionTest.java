package com.cognizant.subscription;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cognizant.subscription.Model.Drug;
import com.cognizant.subscription.Model.Prescription;

public class PrescriptionTest {
	
	private static Prescription prescription;
	private static Drug drug;
	private static SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");	
	
	public static final int EXPECTED_PRESCRIPTION_ID =1;
	public static final int EXPECTED_INSURANCE_POLICY_NUMBER =4567;
	public static final String EXPECTED_INSURANCE_PROVIDER="XYZ";
	public static Date EXPECTED_PRESCRIPTION_DATE;
	public static List<Drug> EXPECTED_DRUGS;
	public static final int EXPECTED_DOSAGE =1;
	public static final String EXPECTED_PRESCRIPTION_COURSE="weekly";
	public static final String EXPECTED_DOCTOR_NAME="Dr. A.K";
	
	public static final int EXPECTED_DRUG_ID =1;
	public static final String EXPECTED_DRUG_NAME ="para";
	public static final int EXPECTED_DRUG_QUANTITY =10;
	
	
	@BeforeClass
	public static void setUp() throws ParseException
	{
		EXPECTED_PRESCRIPTION_DATE=formatter.parse("2020-01-10");		
		
		prescription=new Prescription();		
		prescription.setPrescriptionId(EXPECTED_PRESCRIPTION_ID);
		prescription.setInsurancePolicyNumber(EXPECTED_INSURANCE_POLICY_NUMBER);
		prescription.setInsuranceProvider(EXPECTED_INSURANCE_PROVIDER);
		prescription.setPrescriptionDate(EXPECTED_PRESCRIPTION_DATE);
		prescription.setDosage(EXPECTED_DOSAGE);
		prescription.setPresCourse(EXPECTED_PRESCRIPTION_COURSE);
		prescription.setDoctorName(EXPECTED_DOCTOR_NAME);
		
		drug=new Drug();		
		drug.setQuantity(EXPECTED_DRUG_QUANTITY);		
		drug.setDrugName(EXPECTED_DRUG_NAME);
		
		List<Drug> list=new ArrayList<Drug>();
		list.add(drug);
		EXPECTED_DRUGS=list;	
		prescription.setDrugs(EXPECTED_DRUGS);
		
	}
	
	 @Test
	    public void testPrescriptionDetails() throws Exception {
		 
		 assertEquals(EXPECTED_PRESCRIPTION_ID, prescription.getPrescriptionId());
		 assertEquals(EXPECTED_INSURANCE_POLICY_NUMBER, prescription.getInsurancePolicyNumber());
		 assertEquals(EXPECTED_INSURANCE_PROVIDER, prescription.getInsuranceProvider());
		 assertEquals(EXPECTED_PRESCRIPTION_DATE, prescription.getPrescriptionDate());
		 assertEquals(EXPECTED_DRUGS, prescription.getDrugs());
		 assertEquals(EXPECTED_DOSAGE, prescription.getDosage());
		 assertEquals(EXPECTED_PRESCRIPTION_COURSE, prescription.getPresCourse());
		 assertEquals(EXPECTED_DOCTOR_NAME, prescription.getDoctorName());	
		 assertEquals(("Prescription [prescriptionId=" + prescription.getPrescriptionId() + ", insurancePolicyNumber=" + prescription.getInsurancePolicyNumber()
					+ ", insuranceProvider=" + prescription.getInsuranceProvider() + ", prescriptionDate=" + prescription.getPrescriptionDate() + ", drugs="
					+ prescription.getDrugs() + ", dosage=" + prescription.getDosage() + ", presCourse=" + prescription.getPresCourse() + ", doctorName=" + prescription.getDoctorName() + "]"),prescription.toString());
		
	    }
	

}

