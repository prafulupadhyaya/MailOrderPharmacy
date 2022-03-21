package com.cognizant.portal.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.model.JwtResponse;
import com.cognizant.portal.model.Login;

@FeignClient(name = "Auth-service", url = "http://localhost:8001/api/auth")
public interface AuthClient {

	@PostMapping("/signin")
	ResponseEntity<JwtResponse> authenticateUser(@RequestBody Login login);

	@PostMapping("/validate")
	public boolean validate(@RequestHeader("Authorization") String token);
	@GetMapping("/getMemberId")
	public long memberId(@RequestHeader("Authorization") String token);
	@PostMapping("/register")
	public ResponseEntity<Login> register(@RequestBody Login user);


	
}