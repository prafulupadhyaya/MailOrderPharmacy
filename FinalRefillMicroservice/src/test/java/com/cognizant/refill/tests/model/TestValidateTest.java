package com.cognizant.refill.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.refill.Model.JwtResponse;
import com.cognizant.refill.Model.ValidateResponse;
@ExtendWith(MockitoExtension.class)
public class TestValidateTest {
	private ValidateResponse validateResponse;
	
	@Test
	public void testNoArg() {
		validateResponse=new ValidateResponse();
		validateResponse.setUsername("praful");
		assertEquals("praful",validateResponse.getUsername());
	}
	@Test
	public void testAllArg() {
		validateResponse=new ValidateResponse("praful");
		assertEquals("praful",validateResponse.getUsername());
	}
}
