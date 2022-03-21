package com.cognizant.drugs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DrugsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrugsMicroserviceApplication.class, args);
	}

}
