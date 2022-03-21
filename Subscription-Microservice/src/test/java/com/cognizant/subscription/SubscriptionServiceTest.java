package com.cognizant.subscription;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.subscription.Model.Drug;
import com.cognizant.subscription.Model.Prescription;
import com.cognizant.subscription.Model.Subscription;
import com.cognizant.subscription.Repository.SubscriptionRepository;
import com.cognizant.subscription.Service.Service;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionServiceTest {


		@Autowired
		private Service service;
		
		@MockBean
		private SubscriptionRepository repo;
		
		@Test
		public void testSave() throws Exception {
			Drug drug=new Drug();
			drug.setDrugName("para");
			drug.setQuantity(24);
			List<Drug> dlist=new ArrayList<>();
			dlist.add(drug);
			
			String Date1 = "2020/08/08";
			Date dt = new SimpleDateFormat("dd/mm/yyyy").parse(Date1);
			Prescription pres=new Prescription();
			pres.setDoctorName("xyz");
			pres.setDosage(23);
			pres.setDrugs(dlist);
			pres.setPrescriptionId(1);
			pres.setPrescriptionDate(dt);
			pres.setInsuranceProvider("xyz");
			pres.setInsurancePolicyNumber(123);
			pres.setPresCourse("xyz");
			Subscription sub=new Subscription();
			sub.setSubscriptionId(1);
			sub.setSubscriptionDate(dt);
			sub.setMemberId(1);
			sub.setMemberLocation("ddn");
			sub.setPrescription(pres);
			sub.setRefillOccurence("xyz");
			Mockito.when(repo.save(sub)).thenReturn(sub);
			assertThat(service.save(sub)).isEqualTo(sub);
		}
		
		@Test
		public void testDelete() throws Exception {
			Drug drug=new Drug();
			drug.setDrugName("para");
			drug.setQuantity(24);
			List<Drug> dlist=new ArrayList<>();
			dlist.add(drug);
			
			String Date1 = "2020/08/08";
			Date dt = new SimpleDateFormat("dd/mm/yyyy").parse(Date1);
			Prescription pres=new Prescription();
			pres.setDoctorName("xyz");
			pres.setDosage(23);
			pres.setDrugs(dlist);
			pres.setPrescriptionId(1);
			pres.setPrescriptionDate(dt);
			pres.setInsuranceProvider("xyz");
			pres.setInsurancePolicyNumber(123);
			pres.setPresCourse("xyz");
			Subscription sub=new Subscription();
			sub.setSubscriptionId(1);
			sub.setSubscriptionDate(dt);
			sub.setMemberId(1);
			sub.setMemberLocation("ddn");
			sub.setPrescription(pres);
			sub.setRefillOccurence("xyz");
			service.delete(sub.getSubscriptionId());
			verify(repo,times(1)).deleteBySubscriptionId(sub.getSubscriptionId());
		}
		
		
				
		
	}


