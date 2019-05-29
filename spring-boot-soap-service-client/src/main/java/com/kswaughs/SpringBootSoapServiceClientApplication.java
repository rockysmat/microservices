package com.kswaughs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootSoapServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSoapServiceClientApplication.class, args);
	}

}
