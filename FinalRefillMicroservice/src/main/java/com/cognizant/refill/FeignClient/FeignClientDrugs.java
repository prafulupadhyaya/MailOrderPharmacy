package com.cognizant.refill.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="feignClientDrugs",url="http://localhost:8002")
public interface FeignClientDrugs {
	@GetMapping("/isAvailable/{drug}/{location}")
	boolean isAvailable(@PathVariable String drug,@PathVariable String location,@RequestHeader("Authorization") String token );
}
