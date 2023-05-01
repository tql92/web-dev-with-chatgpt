/*
 * http security is a subset of web security.
 * http security measures encryption, while web security measures hacking in general.
 * 
 * 
 */

package com.example.spring.security;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
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

	// @formatter:off
	 /*
	  * usually, needs a filter chain and a authentication provider
	  * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
	  * https://stackoverflow.com/questions/72381114/spring-security-upgrading-the-deprecated-websecurityconfigureradapter-in-spring
	  * 
	  */
	 // @formatter:on
	@Configuration
	public static class SecurityConfig {

		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
					.authorizeHttpRequests((authz) -> {
						try {
							authz
									// only accounts with DEVS role can access /devs
									.requestMatchers("/devs").hasRole("DEVS")
									// only accounts with USERS role can access /users
									.requestMatchers("/users").hasRole("USERS")
									// annonymous users can access all other resources
									.anyRequest().permitAll()

									.and()

									.formLogin().permitAll()

									.and()

									.logout()
									.logoutUrl("/logout")
									// if users log out, delete cookies and session
									.invalidateHttpSession(true)
									.deleteCookies("JSESSIONID");
						} catch (Exception e) {
							e.printStackTrace();
						}
					})
					.httpBasic();

			return http.build();
		}

		@Bean
		public UserDetailsManager userDetailsService() {
			return new InMemoryUserDetailsManager(Arrays.asList(
					User.withDefaultPasswordEncoder()
							.username("users")
							.password("users")
							.roles("USERS")
							.build(),
					User.withDefaultPasswordEncoder()
							.username("devs")
							.password("devs")
							.roles("DEVS")
							.build()));
		}

	}
}
