package com.amol.ecommerce.product.dto.request;

import java.math.BigDecimal;

public record ProductRequest(String name, String description,
                             String skuCode, BigDecimal price) { }
