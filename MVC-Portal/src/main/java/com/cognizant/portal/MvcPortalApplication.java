package com.cognizant.portal;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MvcPortalApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(MvcPortalApplication.class, args);
	}

}
