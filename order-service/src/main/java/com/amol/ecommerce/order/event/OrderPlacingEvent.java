package com.amol.ecommerce.order.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacingEvent {
    private String orderNumber;
    private String email;
}
