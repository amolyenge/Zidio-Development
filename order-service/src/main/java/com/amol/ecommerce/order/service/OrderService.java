package com.amol.ecommerce.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amol.ecommerce.order.dto.OrderRequest;
import com.amol.ecommerce.order.event.OrderPlacingEvent;
import com.amol.ecommerce.order.feignclient.InventoryClient;
import com.amol.ecommerce.order.model.Order;
import com.amol.ecommerce.order.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacingEvent> kafkaTemplate;

    /**
     * Places an order if the product is in stock and sends an event to Kafka.
     * This method is transactional to ensure data consistency.
     *
     * @param orderRequest the order request DTO
     * @param username the username of the person placing the order
     * @param email the email of the person placing the order
     * @return the order number if successful
     */
    @Transactional
    public String placeOrder(OrderRequest orderRequest, String username, String email) {
        try {
            log.info("User {} ordered {}", username, orderRequest.skuCode());

            // Check if the product is in stock
            boolean isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
            if (isInStock) {
                // Create a new order object
                Order order = new Order();
                order.setOrderNumber(UUID.randomUUID().toString());
                order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
                order.setSkuCode(orderRequest.skuCode());
                order.setQuantity(orderRequest.quantity());
                order.setOrderOwner(username);

                // Save the order to the repository
                Order savedOrder = orderRepository.save(order);

                // Log the successful order placement
                log.info("Order placed successfully with ID: {}", savedOrder.getId());

                // Send the message to Kafka Topic
                OrderPlacingEvent orderPlacingEvent = new OrderPlacingEvent();
                orderPlacingEvent.setOrderNumber(order.getOrderNumber());
                orderPlacingEvent.setEmail(email);
                log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placing", orderPlacingEvent);
                kafkaTemplate.send("order-placing", orderPlacingEvent);
                log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placing", orderPlacingEvent);

                // Return the order number
                return savedOrder.getOrderNumber();
            } else {
                throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
            }

        } catch (Exception e) {
            // Log the exception and rollback the transaction if necessary
            log.error("Error placing order: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to place the order due to: " + e.getMessage());
        }
    }
}
