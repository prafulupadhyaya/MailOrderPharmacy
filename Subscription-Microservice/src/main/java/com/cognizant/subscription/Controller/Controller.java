package com.cognizant.subscription.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.subscription.FeignClients.AuthClient;
import com.cognizant.subscription.FeignClients.FeignClientDrugs;
import com.cognizant.subscription.FeignClients.FeignClientRefill;
import com.cognizant.subscription.Model.Drug;
import com.cognizant.subscription.Model.Prescription;
import com.cognizant.subscription.Model.Subscription;

@RestController
public class Controller {
	@Autowired
	com.cognizant.subscription.Service.Service service;
	@Autowired
	FeignClientDrugs feignClientDrugs;
	@Autowired
	FeignClientRefill feignClientRefill;
	@Autowired
	AuthClient authClient;

	private static final String UNAUTHORIZED_USER= "USER UNAUTHORIZED"; 
	
	@PostMapping("/subscribe/{location}/{refill_occurence}")
	public ResponseEntity<?> subscribe(@RequestBody Prescription medprescription,
			@PathVariable String location, @PathVariable("refill_occurence") String refilloccurence,
			@RequestHeader("Authorization") String token) {
		if (!authClient.validate(token)) {
			throw new com.cognizant.subscription.Exception.TokenInvalidException(UNAUTHORIZED_USER);
		}
		Prescription p=medprescription;
		Subscription subscription = new Subscription();
		subscription.setMemberId(authClient.memberId(token));
		subscription.setMemberLocation(location);
		subscription.setPrescription(p);
	
		List<Drug> drugs = medprescription.getDrugs();
		
		for (Drug drug : drugs) {
			String d=drug.getDrugName();
		
			if (!feignClientDrugs.isAvailable(d,location,token)) {
				return new ResponseEntity<>("cannot be proceded", HttpStatus.NOT_FOUND);
			}
		}
		subscription.setSubscriptionDate(new Date());
		subscription.setRefillOccurence(refilloccurence);
	
		Subscription subs = service.save(subscription);
		int subscriptionId = subs.getSubscriptionId();
		feignClientRefill.firstRequestRefill(subscriptionId, location, refilloccurence, token);
		return new ResponseEntity<>(subs, HttpStatus.CREATED);
	}

	@DeleteMapping("/unsubscribe/{subscription_id}/")
	public ResponseEntity<?> deleteSubscription(@PathVariable("subscription_id") int subscriptionid,
			@RequestHeader("Authorization") String token) {
		if (!authClient.validate(token)) {
			throw new com.cognizant.subscription.Exception.TokenInvalidException(UNAUTHORIZED_USER);
		} 	
		if (feignClientRefill.anyPendings(subscriptionid, token)) {
			return new ResponseEntity<>("you have not yet cleared all the dues", HttpStatus.PAYMENT_REQUIRED);
		}
		service.delete(subscriptionid);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getAllSubIdOfMember/{memberId}")
	public List<Integer> getAllSubIdOfMember(@RequestHeader("Authorization") String token, @PathVariable long memberId){
		if (!authClient.validate(token)) {
			throw new com.cognizant.subscription.Exception.TokenInvalidException(UNAUTHORIZED_USER);
		}
		return service.getSubIdByMemberId(memberId);
		
	}
	
	@GetMapping("/getAllDrugsOfMember/{id}")
	public Map<String, Integer> getAllDrugsOfMember(@PathVariable int id) {
		
		
		return service.getAllDrugsOfMember(id);
	}
	
	@GetMapping("/getRefillOccurence/{subscriptionId}")
	public String getRefillOccurence(@PathVariable int subscriptionId) {
		return service.findRefillOccurenceBySubscriptionId(subscriptionId);
	}
}
