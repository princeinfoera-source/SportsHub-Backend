package com.infoera.sportshub_inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SportshubInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportshubInventoryApplication.class, args);
	}

}
