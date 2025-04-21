package com.amol.ecommerce.order;

import org.springframework.boot.SpringApplication;

import com.amol.ecommerce.order.OrderServiceApplication;

public class TestOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
