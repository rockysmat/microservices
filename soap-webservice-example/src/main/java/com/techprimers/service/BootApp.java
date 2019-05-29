package com.techprimers.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.techprimers.*"})
public class BootApp {

	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
	}

}
