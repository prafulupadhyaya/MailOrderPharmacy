package com.cognizant.drugs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends RuntimeException{

	
		public LocationNotFoundException(String msg) {
			super(msg);
		}
}
