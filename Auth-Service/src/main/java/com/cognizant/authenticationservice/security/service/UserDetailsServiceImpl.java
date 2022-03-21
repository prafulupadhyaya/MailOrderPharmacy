package com.cognizant.authenticationservice.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.authenticationservice.entity.User;
import com.cognizant.authenticationservice.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> users = userRepository.findByUsername(username);
		users.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return users.map(UserDetailsImpl::new).get();
	}

	public User save(User user) {
		User newUser = new User();
		return userRepository
				.save(new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), "ROLE_USER"));

	}
}
