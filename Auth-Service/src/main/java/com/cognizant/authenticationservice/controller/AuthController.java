package com.cognizant.authenticationservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authenticationservice.entity.User;
import com.cognizant.authenticationservice.exception.TokenInvalidException;
import com.cognizant.authenticationservice.payload.JwtResponse;
import com.cognizant.authenticationservice.payload.LoginRequest;
import com.cognizant.authenticationservice.repository.UserRepository;
import com.cognizant.authenticationservice.security.jwt.JwtUtils;
import com.cognizant.authenticationservice.security.service.UserDetailsImpl;
import com.cognizant.authenticationservice.security.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final JwtUtils jwtUtils;
	private final UserDetailsServiceImpl userDetailsService;
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, UserDetailsServiceImpl userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.jwtUtils = jwtUtils;
		this.userDetailsService = userDetailsService;
	}
	

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		log.debug("Login Request {}", loginRequest);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return new ResponseEntity<>(new JwtResponse(userDetails.getId(), jwt, userDetails.getUsername(), roles),
				HttpStatus.OK);
	}

	@PostMapping("/validate")
	public boolean validate(@RequestHeader("Authorization") String token) {
		log.debug("in auth controller with jwt {}", token);
		return jwtUtils.validateJwtToken(token);
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		log.debug("in auth controller register" + user.getUsername());

		return ResponseEntity.ok(userDetailsService.save(user));

	}
	@GetMapping("/getMemberId")
	public long memberId(@RequestHeader("Authorization") String token) {
		boolean response = jwtUtils.validateJwtToken(token);

		if (response) {
			String username = jwtUtils.getUserNameFromJwtToken(token);
			User user = userRepository.findByUsername(username).get();
			return user.getId();
		} else
			throw new TokenInvalidException("UNAUTHORISED");

	}	

}
