package com.zti.partpicker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Main entrypoint of Spring Boot application that starts Spring Context,
 * loads all needed beans and starts backend application on localhost:8080
 */
@EntityScan("com.zti.partpicker.*")
@SpringBootApplication
public class PartpickerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartpickerApplication.class, args);
	}

}
