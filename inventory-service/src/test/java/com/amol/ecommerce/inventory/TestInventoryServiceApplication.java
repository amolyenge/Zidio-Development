package com.amol.ecommerce.inventory;

import org.springframework.boot.SpringApplication;

import com.amol.ecommerce.inventory.InventoryServiceApplication;

public class TestInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventoryServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
