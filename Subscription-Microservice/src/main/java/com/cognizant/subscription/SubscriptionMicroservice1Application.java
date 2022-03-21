package com.cognizant.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubscriptionMicroservice1Application {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionMicroservice1Application.class, args);
	}

}
