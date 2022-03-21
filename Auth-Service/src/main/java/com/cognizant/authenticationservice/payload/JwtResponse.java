package com.cognizant.authenticationservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	private long id;
	private String token;
	private String username;
	private List<String> roles;
}
