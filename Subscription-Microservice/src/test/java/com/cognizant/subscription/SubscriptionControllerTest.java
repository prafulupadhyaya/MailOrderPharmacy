package com.cognizant.subscription;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cognizant.subscription.Controller.Controller;
import com.cognizant.subscription.FeignClients.AuthClient;
import com.cognizant.subscription.FeignClients.FeignClientDrugs;
import com.cognizant.subscription.FeignClients.FeignClientRefill;
import com.cognizant.subscription.Model.Drug;
import com.cognizant.subscription.Model.Prescription;
import com.cognizant.subscription.Model.Subscription;
import com.cognizant.subscription.Service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = Controller.class)
public class SubscriptionControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private Service subsService;
	@MockBean
	private AuthClient authClient;
	@MockBean
	FeignClientDrugs feignClientDrugs;
	@MockBean
	FeignClientRefill feignClientRefill;
	@Autowired
	private ObjectMapper objectMapper;	
	
	private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");

	

	@Test
	public void testSubscribeCreated() throws Exception { // Status - Created (201)
		Drug d1=new Drug();
		Drug d2=new Drug();
		d1.setDrugName("para");
		d1.setQuantity(10);
		d2.setDrugName("syrup");
		d2.setQuantity(20);
		
		List<Drug> listDrugs=new ArrayList<>();
		listDrugs.add(d1);
		listDrugs.add(d2);
		Prescription p=new Prescription();
		Subscription mockSubscription=new Subscription();
		p.setDoctorName("Dr.AK");
		p.setDosage(10);
		p.setDrugs(listDrugs);
		p.setPrescriptionId(100);
		p.setPrescriptionDate(formatter.parse("2020-02-01"));
		p.setInsuranceProvider("XYZ");
		p.setInsurancePolicyNumber(9876);
		p.setPresCourse("presCourse");
		mockSubscription.setSubscriptionId(1);
		mockSubscription.setSubscriptionDate(formatter.parse("2020-02-10"));
		mockSubscription.setMemberId(1234);
		mockSubscription.setMemberLocation("ddun");
		mockSubscription.setPrescription(p);
		mockSubscription.setRefillOccurence("weekly");
		String token="hello";
		Mockito.when(authClient.validate(token)).thenReturn(true);
		Mockito.when(feignClientDrugs.isAvailable(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		Mockito.when(subsService.save(Mockito.any(Subscription.class))).thenReturn(mockSubscription);
		Mockito.when(feignClientRefill.firstRequestRefill(Mockito.anyInt(),Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(new ResponseEntity<>(null,HttpStatus.CREATED));
		
		String mockSubscriptionJson=objectMapper.writeValueAsString(mockSubscription);
		String prescriptionJson=objectMapper.writeValueAsString(p);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/subscribe/ddun/weekly")
				.accept(MediaType.APPLICATION_JSON).content(prescriptionJson)
				.contentType(MediaType.APPLICATION_JSON).header("Authorization", token);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String actualResult=result.getResponse().getContentAsString();
		//System.out.println(actualResult);
		
		assertEquals(mockSubscriptionJson,actualResult);	
	}
	
	@Test
	public void testSubscribeNotFound() throws Exception { // Status - NotFound
		Drug d1=new Drug();
		Drug d2=new Drug();
		d1.setDrugName("para");
		d1.setQuantity(10);
		d2.setDrugName("syrup");
		d2.setQuantity(20);
		List<Drug> listDrugs=new ArrayList<>();
		listDrugs.add(d1);
		listDrugs.add(d2);
		Prescription p=new Prescription();
		Subscription mockSubscription=new Subscription();
		p.setDoctorName("Dr.AK");
		p.setDosage(10);
		p.setDrugs(listDrugs);
		p.setPrescriptionId(100);
		p.setPrescriptionDate(formatter.parse("2020-02-01"));
		p.setInsuranceProvider("XYZ");
		p.setInsurancePolicyNumber(9876);
		p.setPresCourse("presCourse");
		mockSubscription.setSubscriptionId(1);
		mockSubscription.setSubscriptionDate(formatter.parse("2020-02-10"));
		mockSubscription.setMemberId(1234);
		mockSubscription.setMemberLocation("ddun");
		mockSubscription.setPrescription(p);
		mockSubscription.setRefillOccurence("weekly");
		String token="hello";
		Mockito.when(authClient.validate(token)).thenReturn(true);
		
		
		Mockito.when(feignClientDrugs.isAvailable(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		Mockito.when(subsService.save(Mockito.any(Subscription.class))).thenReturn(mockSubscription);
		Mockito.when(feignClientRefill.firstRequestRefill(Mockito.anyInt(),Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(new ResponseEntity<>(null,HttpStatus.CREATED));
		
		
		String prescriptionJson=objectMapper.writeValueAsString(p);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/subscribe/ddun/weekly")
				.accept(MediaType.APPLICATION_JSON).content(prescriptionJson)
				.contentType(MediaType.APPLICATION_JSON).header("Authorization", token);
		
		MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();
		int actual=result.getResponse().getStatus();
		int expected=404;
		
//		System.out.println(result.getResponse().getStatus());
//		System.out.println(HttpStatus.NOT_FOUND);
		assertEquals(expected,actual);	
	}

	
	@Test
	public void testDeleteSubscriptionSuccess() throws Exception { //Status - OK
		Drug d1=new Drug();
		Drug d2=new Drug();
		d1.setDrugName("para");
		d1.setQuantity(10);
		d2.setDrugName("syrup");
		d2.setQuantity(20);
		List<Drug> listDrugs=new ArrayList<>();
		listDrugs.add(d1);
		listDrugs.add(d2);
		Prescription p=new Prescription();
		Subscription mockSubscription=new Subscription();
		p.setDoctorName("Dr.AK");
		p.setDosage(10);
		p.setDrugs(listDrugs);
		p.setPrescriptionId(100);
		p.setPrescriptionDate(formatter.parse("2020-02-01"));
		p.setInsuranceProvider("XYZ");
		p.setInsurancePolicyNumber(9876);
		p.setPresCourse("presCourse");
		mockSubscription.setSubscriptionId(1);
		mockSubscription.setSubscriptionDate(formatter.parse("2020-02-10"));
		mockSubscription.setMemberId(1234);
		mockSubscription.setMemberLocation("ddun");
		mockSubscription.setPrescription(p);
		mockSubscription.setRefillOccurence("weekly");
		String token="hello";
		Mockito.when(authClient.validate(token)).thenReturn(true);
		
		Mockito.when(feignClientRefill.anyPendings(Mockito.anyInt(),Mockito.anyString())).thenReturn(false);
		
		
		String prescriptionJson=objectMapper.writeValueAsString(p);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/unsubscribe/1/")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).header("Authorization", token);
		
		mockMvc.perform(requestBuilder).andExpect(status().isOk());		
	}

	@Test
	public void testDeleteSubscriptionFail() throws Exception { //	Status - PAYMENT_REQUIRED
		Drug d1=new Drug();
		Drug d2=new Drug();
		d1.setDrugName("para");
		d1.setQuantity(10);
		d2.setDrugName("syrup");
		d2.setQuantity(20);
		List<Drug> listDrugs=new ArrayList<>();
		listDrugs.add(d1);
		listDrugs.add(d2);
		Prescription p=new Prescription();
		Subscription mockSubscription=new Subscription();
		p.setDoctorName("Dr.AK");
		p.setDosage(10);
		p.setDrugs(listDrugs);
		p.setPrescriptionId(100);
		p.setPrescriptionDate(formatter.parse("2020-02-01"));
		p.setInsuranceProvider("XYZ");
		p.setInsurancePolicyNumber(9876);
		p.setPresCourse("presCourse");
		mockSubscription.setSubscriptionId(1);
		mockSubscription.setSubscriptionDate(formatter.parse("2020-02-10"));
		mockSubscription.setMemberId(1234);
		mockSubscription.setMemberLocation("ddun");
		mockSubscription.setPrescription(p);
		mockSubscription.setRefillOccurence("weekly");
		String token="hello";
		Mockito.when(authClient.validate(token)).thenReturn(true);

		Mockito.when(feignClientRefill.anyPendings(Mockito.anyInt(),Mockito.anyString())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/unsubscribe/1/")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).header("Authorization", token);
		
		mockMvc.perform(requestBuilder).andExpect(status().isPaymentRequired());		
	}
	
	
	

}

