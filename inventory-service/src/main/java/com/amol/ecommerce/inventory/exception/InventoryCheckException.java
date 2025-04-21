package com.amol.ecommerce.inventory.exception;

public class InventoryCheckException extends RuntimeException {

    public InventoryCheckException(String message) {
        super(message);
    }

    public InventoryCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
