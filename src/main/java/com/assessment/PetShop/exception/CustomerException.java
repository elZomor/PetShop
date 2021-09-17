package com.assessment.PetShop.exception;

public class CustomerException extends RuntimeException{
    public CustomerException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
