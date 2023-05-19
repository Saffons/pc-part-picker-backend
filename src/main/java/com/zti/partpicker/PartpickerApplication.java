package com.zti.partpicker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.zti.partpicker.*")
@SpringBootApplication
public class PartpickerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartpickerApplication.class, args);
	}

}
