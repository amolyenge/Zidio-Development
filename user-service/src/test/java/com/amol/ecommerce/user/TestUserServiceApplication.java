package com.amol.ecommerce.user;

import org.springframework.boot.SpringApplication;

import com.amol.ecommerce.user.UserServiceApplication;

public class TestUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(UserServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
