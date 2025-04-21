package com.amol.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amol.ecommerce.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
