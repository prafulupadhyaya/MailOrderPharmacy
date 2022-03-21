package com.cognizant.refill;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class RefillMicroserviceApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(RefillMicroserviceApplication.class, args);
	}

	
}
