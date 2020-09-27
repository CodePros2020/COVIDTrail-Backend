package com.covidtrail.covidtrailbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@CrossOrigin(origins = "https://covidtrail-backend.azurewebsites.net")
public class CovidtrailbackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(CovidtrailbackendApplication.class, args);
		System.out.println("Project started and now running...");
	}
}
