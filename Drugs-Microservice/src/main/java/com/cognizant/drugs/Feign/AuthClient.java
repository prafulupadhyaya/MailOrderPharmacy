package com.cognizant.drugs.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(url="http://localhost:8001",name="Auth-service")
public interface AuthClient {
	
	@PostMapping("/api/auth/validate")
	public boolean validate(@RequestHeader("Authorization") String token);


}


