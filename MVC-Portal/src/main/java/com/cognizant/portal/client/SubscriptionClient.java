package com.cognizant.portal.client;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.model.Prescription;

@FeignClient(name = "Subscription-service", url = "http://localhost:8081")
public interface SubscriptionClient {
	@PostMapping("/subscribe/{location}/{refill_occurence}")
	public ResponseEntity<?> subscribe(@RequestBody Prescription prescription, @PathVariable String location,
			@PathVariable String refill_occurence, @RequestHeader("Authorization") String token);

	@DeleteMapping("/unsubscribe/{subscription_id}/")
	public ResponseEntity<?> deleteSubscription(@PathVariable int subscription_id,
			@RequestHeader("Authorization") String token);
	
	@GetMapping("/getAllDrugsOfMember/{id}")
	public Map<String, Integer> getAllDrugsOfMember(@PathVariable int id);
	@GetMapping("/getAllSubIdOfMember/{memberId}")
	public List<Integer> getAllSubIdOfMember(@RequestHeader("Authorization") String token, @PathVariable long memberId);

}
