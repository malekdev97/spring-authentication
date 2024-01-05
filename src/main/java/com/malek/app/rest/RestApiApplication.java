package com.malek.app.rest;

import com.malek.app.rest.Model.Company;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		// SpringApplication.run(RestApiApplication.class, args);

		Company<String> objCompany = new Company<String>();

		objCompany.setName("STC");
		objCompany.setCountry("Saudi Arabia");
		objCompany.setCity("Riyadh");



		System.out.println("Hello World!");
	}

	// this doesn't work why?
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name") String name) {
		return String.format("Hello %s!", name);
	}

}
