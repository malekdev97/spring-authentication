package com.spring.implementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
@Controller
public class ResetPasswordApplication {

	public static void main(String[] args) {
		 SpringApplication.run(ResetPasswordApplication.class, args);
		
	}

	@GetMapping("/")
	public String home() {
		return "home";
	}

}
