package com.example.Restoran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com/example/Restoran/model")
public class RestoranApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestoranApplication.class, args);
	}

}