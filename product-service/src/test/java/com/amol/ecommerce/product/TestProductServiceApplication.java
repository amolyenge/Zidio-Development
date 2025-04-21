package com.amol.ecommerce.product;

import org.springframework.boot.SpringApplication;

import com.amol.ecommerce.product.ProductServiceApplication;

public class TestProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
