package com.cognizant.refill.FeignClient;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="feignClientSubscribe",url="http://localhost:8081")
public interface FeignClientSubscribe {
	
	@GetMapping("/getAllDrugsOfMember/{memberId}")
	Map<String, Integer> getAllDrugsOfMember(@PathVariable int memberId );
	
	@GetMapping("/getAllSubIdOfMember/{memberId}")
	public List<Integer> getAllSubIdOfMember(@RequestHeader("Authorization") String token, @PathVariable long memberId);

	@GetMapping("/getRefillOccurence/{subscriptionId}")
	public String getRefillOccurence(@PathVariable int subscriptionId);
	
}
