package com.cognizant.subscription;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cognizant.subscription.Model.Drug;

public class DrugTest {
	
	private static Drug drug;
	
	public static final int EXPECTED_DRUG_ID =1;
	public static final String EXPECTED_DRUG_NAME ="paracetamol";
	public static final int EXPECTED_DRUG_QUANTITY =10;
	
	
	@BeforeClass
	public static void setUp()
	{
		drug=new Drug();		
		drug.setDrugName(EXPECTED_DRUG_NAME);		
		drug.setQuantity(EXPECTED_DRUG_QUANTITY);
	}
	
	
	 	@Test
	    public void testDrugDetails() throws Exception {
		 
		 assertEquals(EXPECTED_DRUG_NAME, drug.getDrugName());
		 assertEquals(EXPECTED_DRUG_QUANTITY, drug.getQuantity());	 

	    }

}
