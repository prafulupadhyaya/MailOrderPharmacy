package com.cognizant.authenticationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognizant.authenticationservice.entity.User;
import com.cognizant.authenticationservice.repository.UserRepository;

@SpringBootApplication
public class AuthenticationServiceApplication implements CommandLineRunner {

	private UserRepository userRepository;

	@Autowired
	public AuthenticationServiceApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("user", "pass", "ROLE_ADMIN"));
		userRepository.save(new User("srideep", "srideep", "ROLE_ADMIN"));
		
	}
}
