package com.cognizant.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.portal.client.AuthClient;
import com.cognizant.portal.model.Login;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private AuthClient authServiceClient;

	@Override
	public String registerUserNameAndPassword(Login user) {
		ResponseEntity<Login> response = null;
		try {
			response = authServiceClient.register(user);
			if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
				log.debug("Body: {} ", response.getBody());
				return "login";
			}
		} catch (FeignException e) {
			log.error("Exceptions occurred while processing the Register request::" + e.getMessage());
			return "signup";
		}
		return null;
	}
}
