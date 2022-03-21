package com.cognizant.drugs.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.drugs.Feign.AuthClient;
import com.cognizant.drugs.Model.Drugs;
import com.cognizant.drugs.Service.DrugsService;
import com.cognizant.drugs.exception.TokenInvalidException;

@RestController
public class DrugsController {
	@Autowired
	DrugsService drugsService;
	@Autowired
	private AuthClient authClient;
	
	private static final String UNAUTHORIZED_USER= "USER UNAUTHORIZED"; 

	@GetMapping("/searchDrugsByID/{drugId}")
	public ResponseEntity<?> searchDrugsByID(@PathVariable int drugId,
			@RequestHeader("Authorization") String token)throws TokenInvalidException {
		if (!authClient.validate(token)) {
			throw new TokenInvalidException(UNAUTHORIZED_USER);
		}
		Drugs drug = drugsService.searchDrugsByID(drugId).orElse(null);
		if (drug != null)
			return new ResponseEntity<>(drug, HttpStatus.OK);
		return new ResponseEntity<>(drug, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/searchDrugsByName/{name}")
	public ResponseEntity<Drugs> searchDrugsByName(@PathVariable String name,
			@RequestHeader("Authorization") String token) throws TokenInvalidException {
		if (!authClient.validate(token)) {
			throw new TokenInvalidException(UNAUTHORIZED_USER);
		}
		Drugs drug = drugsService.searchDrugsByName(name).orElse(null);
		if (drug != null)
			return new ResponseEntity<>(drug, HttpStatus.OK);
		return new ResponseEntity<>(drug, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getDispatchableDrugStock/{drugId}/{location}")
	public ResponseEntity<Drugs> getDispatchableDrugStock(@PathVariable int drugId, @PathVariable String location,
			@RequestHeader("Authorization") String token) throws TokenInvalidException{
		if (!authClient.validate(token)) {
			throw new TokenInvalidException(UNAUTHORIZED_USER);
		}
		Drugs drug = drugsService.findByLocation(drugId, location);
	
		return new ResponseEntity<>(drug, HttpStatus.OK);
	}

	@PostMapping("/drugsAdd")
	public void save(@RequestBody Drugs med,
			@RequestHeader("Authorization") String token) throws TokenInvalidException {
		if (!authClient.validate(token)) {
			throw new TokenInvalidException(UNAUTHORIZED_USER);
		}
		drugsService.save(med);
	}

	@GetMapping("/isAvailable/{drug}/{location}")
	public boolean isAvailable(@PathVariable String drug, @PathVariable String location,
			@RequestHeader("Authorization") String token) throws TokenInvalidException {
		if (!authClient.validate(token)) {
			throw new TokenInvalidException(UNAUTHORIZED_USER);
		}
		return drugsService.isAvailable(drug, location);
	}
}
