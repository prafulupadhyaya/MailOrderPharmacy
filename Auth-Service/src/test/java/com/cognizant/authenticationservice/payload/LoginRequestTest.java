package com.cognizant.authenticationservice.payload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.Test;

public class LoginRequestTest {

	@Test
	public void getDataTest() {
		
		LoginRequest login=new LoginRequest("srideep","srideep");
		assertEquals("srideep",login.getUsername());
		assertEquals("srideep",login.getPassword());	
		assertNotNull(login.toString());
		assertNotNull(login.getUsername());
		assertNotNull(login.getPassword());
	}
	
	@Test
	public void setDataTest() {
		
		
		LoginRequest login=new LoginRequest();
		login.setUsername("praful");
		login.setPassword("praful");
		assertNotNull(login.toString());
		assertNotNull(login.getUsername());
		assertNotNull(login.getPassword());
		
}
}