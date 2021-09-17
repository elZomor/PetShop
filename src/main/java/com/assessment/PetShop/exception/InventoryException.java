package com.assessment.PetShop.exception;

public class InventoryException extends RuntimeException{
    public InventoryException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
