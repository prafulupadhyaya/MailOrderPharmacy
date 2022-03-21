package com.cognizant.refill.Model;


import java.util.List;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	private long id;
	private String token;
	private String username;
	private List<String> roles;
}
