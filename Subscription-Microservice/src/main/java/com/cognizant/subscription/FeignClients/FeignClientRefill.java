package com.cognizant.subscription.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
@FeignClient(name="feignClientfirstRequestRefill",url="http://localhost:8080")
public interface FeignClientRefill {

	@PostMapping("/firstRequestRefill/{Subscription_ID}/{location}/{refill_occurence}")
	public ResponseEntity<?> firstRequestRefill(@PathVariable("Subscription_ID") int subscriptionId,@PathVariable String location,@PathVariable("refill_occurence") String refilloccurence,@RequestHeader("Authorization")String token);
	@GetMapping("/anyPendings/{subscription_id}")
	public boolean anyPendings(@PathVariable("subscription_id") int subscriptionid,@RequestHeader("Authorization")String token);
}
