package com.cognizant.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.portal.client.AuthClient;
import com.cognizant.portal.model.JwtResponse;
import com.cognizant.portal.model.Login;

import feign.FeignException;

@Service
public class LoginServiceImpl implements LoginService {

	private final AuthClient authServiceClient;

	@Autowired
	public LoginServiceImpl(AuthClient authServiceClient) {
		this.authServiceClient = authServiceClient;
	}

	@Override
	public String validateUserNameAndPassword(Login login) {
		ResponseEntity<JwtResponse> response = null;
		try {
			response = authServiceClient.authenticateUser(login);
			if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
				// log.debug("Body: {} ", response.getBody());
				return response.getBody().getToken();
			} else {
				return "login";
			}
		} catch (FeignException e) {
			// log.error("Exceptions occurred while processing the Login request::");

		}
		return null;
	}

	@Override
	public String getUsername(Login login) {
		ResponseEntity<JwtResponse> response = null;
		try {
			response = authServiceClient.authenticateUser(login);
			if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
				// log.debug("Body: {} ", response.getBody());
				return response.getBody().getUsername();
			} else {
				return "login";
			}
		} catch (FeignException e) {
			// log.error("Exceptions occurred while processing the Login request::");

		}
		return null;
	}
}
