package com.rv.Online_Video_Rental_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OnlineVideoRentalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineVideoRentalSystemApplication.class, args);
	}

}
