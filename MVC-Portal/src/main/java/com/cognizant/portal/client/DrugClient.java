package com.cognizant.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.model.Drugs;



@FeignClient(name = "Drug-service", url = "http://localhost:8002")
public interface DrugClient {

	@GetMapping("/searchDrugsByID/{drugId}")
	public ResponseEntity<Drugs> searchDrugsByID(@RequestHeader("Authorization") String token,@PathVariable int drugId);
	
	@GetMapping("/searchDrugsByName/{name}")
	public ResponseEntity<Drugs> searchDrugsByName(@RequestHeader("Authorization") String token,@PathVariable String name);
}
