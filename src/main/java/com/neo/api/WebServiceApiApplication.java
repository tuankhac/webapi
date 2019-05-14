package com.neo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableCaching //enables Spring Caching functionality
public class WebServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServiceApiApplication.class, args);
	}

}
