package com.cognizant.portal.client;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.portal.model.RefillOrder;
import com.cognizant.portal.model.RefillOrderLine;


@FeignClient(name = "Refill-service", url = "http://localhost:8080")
public interface RefillClient {
	@GetMapping("/viewRefillStatusAll/{subscriptionId}")
	public List<RefillOrder> viewRefillStatusAll(@PathVariable int subscriptionId,@RequestHeader("Authorization") String token) ;	
	@GetMapping("/viewRefillStatus/{subscriptionId}")
	public List<RefillOrder> viewRefillStatus(@PathVariable int subscriptionId,@RequestHeader("Authorization") String token);

	@PostMapping("/requestAdhocRefill")
	public ResponseEntity<?> requestAdhocRefill(@RequestParam("Subscription_ID") int subscriptionId,@RequestParam("Policy_ID") int policyId,@RequestParam("Location") String location,@RequestBody List<RefillOrderLine> morePrescription,@RequestHeader("Authorization") String token  );
	@GetMapping("/getRefillDuesAsOfDate")
	public List<RefillOrder> getRefillDuesAsOfDate(@RequestParam("date") String date,@RequestParam("Subscription_ID") int subscriptionid,@RequestHeader("Authorization") String token) throws ParseException;
	@GetMapping("/anyPendings/{subscription_id}")
	public boolean anyPendings(@PathVariable("subscription_id") int subscriptionid,@RequestHeader("Authorization") String token);
	@PostMapping("/requestRefill")
	public ResponseEntity<?> requestRefill(@RequestParam("Subscription_ID") int subscriptionId,@RequestHeader("Authorization") String token);
	@PostMapping("/firstRequestRefill/{Subscription_ID}/{location}/{refill_occurence}")
	public ResponseEntity<?> firstRequestRefill(@PathVariable("Subscription_ID") int subscriptionId,@PathVariable String location,@PathVariable String refill_occurence,@RequestHeader("Authorization") String token);
}
