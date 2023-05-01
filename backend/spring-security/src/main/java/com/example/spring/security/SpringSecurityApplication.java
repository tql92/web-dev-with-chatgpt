/*
 * http security is a subset of web security.
 * http security measures encryption, while web security measures hacking in general.
 * 
 * 
 */

package com.example.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@RestController
	public static class Home {

		@GetMapping("/users")
		public String users() {
			return "/users working!";
		}

		@GetMapping("/devs")
		public String devs() {
			return "/devs working!";
		}

	}
}
