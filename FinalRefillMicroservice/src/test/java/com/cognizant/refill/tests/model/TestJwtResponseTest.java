package com.cognizant.refill.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.refill.Model.JwtResponse;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@ExtendWith(MockitoExtension.class)
public class TestJwtResponseTest {
	private JwtResponse jwtResponse;
	@Test
	public void testAllGettersAndSetters() throws ParseException {
		jwtResponse=new JwtResponse();
		jwtResponse.setId(1);
		jwtResponse.setToken("hello");
		jwtResponse.setUsername("praful");
		jwtResponse.setRoles(Arrays.asList( "xyz"));
		
		assertEquals(1, jwtResponse.getId());
		assertEquals("hello", jwtResponse.getToken());
		assertEquals("praful", jwtResponse.getUsername());
		assertEquals(Arrays.asList( "xyz"), jwtResponse.getRoles());
	}
	@Test
	public void testAllGettersAndSettersParameterized() throws ParseException {
		jwtResponse=new JwtResponse(1,"hello","praful",Arrays.asList( "xyz"));
		
		
		assertEquals(1, jwtResponse.getId());
		assertEquals("hello", jwtResponse.getToken());
		assertEquals("praful", jwtResponse.getUsername());
		assertEquals(Arrays.asList( "xyz"), jwtResponse.getRoles());
	}
}
