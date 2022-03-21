package com.cognizant.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
	private long id;
	private String token;
	private String username;
	private List<String> roles;
}
