package com.amol.ecommerce.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.amol.ecommerce.product.model.Product;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findBySkuCode(String skuCode);
}
