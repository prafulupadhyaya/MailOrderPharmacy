package com.cognizant.authenticationservice.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.Test;

public class UserTest {
	
	@Test
	public void test(){
	User user= new User();
	user.setId(1);
    user.setPassword("srideep");
    user.setRoles("ROLE_USER");
    user.setUsername("srideep");

    User user1=new User("srideep","srideep","ROLE_USER");
    assertNotNull(user1);
    assertNotNull(user);
    assertNotNull(user.toString());
	}
	
	@Test
	public void getDataTest() {
		
//		List<String> roles=new ArrayList<>(); 
//		roles.add("ROLE_USER");
		User user =new User("srideep","srideep","ROLE_USER");
		user.setId(1);
		assertNotNull(user.getId());
		assertNotNull(user.getPassword());
		assertNotNull(user.getRoles());
		assertNotNull(user.getUsername());
		assertEquals("srideep",user.getUsername());
		assertEquals(1,user.getId());
		assertEquals("srideep",user.getPassword());
		assertEquals("ROLE_USER",user.getRoles());
		
		
	}
	
	@Test
	public void setDataTest() {
		
		
		User user =new User();
		user.setId(5);
		user.setUsername("srideep");
		user.setPassword("srideep");
		user.setRoles("ROLE_USER");
		assertNotNull(user.getId());
		assertNotNull(user.getPassword());
		assertNotNull(user.getRoles());
		assertNotNull(user.getUsername());
		assertEquals(5,user.getId());
		assertEquals("srideep",user.getUsername());
		assertEquals("srideep",user.getPassword());
		assertEquals("ROLE_USER",user.getRoles());
		assertNotNull(user.toString());
	}
	
	
}
