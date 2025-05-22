package com.backend.shopee.shopee_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@SpringBootApplication
public class ShopeeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopeeBackendApplication.class, args);
	}

}
