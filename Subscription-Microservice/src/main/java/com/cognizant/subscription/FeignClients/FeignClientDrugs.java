package com.cognizant.subscription.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="Drug-service",url="http://localhost:8002")
public interface FeignClientDrugs {
	@GetMapping("/isAvailable/{drug}/{location}")
	boolean isAvailable(@PathVariable String drug,@PathVariable String location,@RequestHeader("Authorization") String token);
}
