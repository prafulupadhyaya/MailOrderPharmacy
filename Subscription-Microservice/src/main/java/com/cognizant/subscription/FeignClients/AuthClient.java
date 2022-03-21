package com.cognizant.subscription.FeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url="http://localhost:8001",name="Auth-service")
public interface AuthClient {
	
	@PostMapping("/api/auth/validate")
	public boolean validate(@RequestHeader("Authorization") String token);
	
	@GetMapping("/api/auth/getMemberId")
	public long memberId(@RequestHeader("Authorization") String token);
}	





