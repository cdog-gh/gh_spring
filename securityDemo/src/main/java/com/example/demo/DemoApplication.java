package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class DemoApplication {

	/*
	https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#application-properties.security
	to test role auth, I set spring.security.user.roles = USER
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
