package com.rangga.security.web;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rangga.security.web.filter.CustomeAuthenticationFilter;
import com.rangga.security.web.model.Role;
import com.rangga.security.web.model.User;
import com.rangga.security.web.service.UserService;

@SpringBootApplication
public class SpringbootSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSecurityJwtApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new  BCryptPasswordEncoder();
	};
	
	
	
	//@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new User(null, "Rose Mania", "rose", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Billy Martin", "billy", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Megalodon", "mega", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Rita Suera", "rita", "1234", new ArrayList<>()));
		
			userService.addRoleToUser("rose", "ROLE_USER");
			userService.addRoleToUser("billy", "ROLE_ADMIN");
			userService.addRoleToUser("billy", "ROLE_MANAGER");
			userService.addRoleToUser("rita", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("mega", "ROLE_MANAGER");
			userService.addRoleToUser("rita", "ROLE_ADMIN");
		};
	}
	
}
