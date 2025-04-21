package com.amol.ecommerce.order.dto;

import java.math.BigDecimal;

public record OrderRequest(
        Long orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
