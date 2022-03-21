package com.cognizant.authenticationservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.authenticationservice.repository.UserRepository;

/**
 * @author POD 13
 *
 */
@SpringBootTest
public class AuthenticationServiceApplicationTests {

	/**
	 * 
	 */
	@Autowired
	private AuthenticationServiceApplication asa;
	
	/**
	 * 
	 */
	@MockBean
	private UserRepository userRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
    /**
     * 
     */
    @Test
    void contextLoads() {
    	String[] args = {};
    	AuthenticationServiceApplication.main(args);
    }

}
