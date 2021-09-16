package com.assessment.PetShop.exception;

public class DogNotFoundException extends RuntimeException {
    public DogNotFoundException(Integer id) {
        super (String.format("Dog with id: %s is not found",id));
    }

    public DogNotFoundException() {
        super ("No dogs in the list");
    }

    public DogNotFoundException(String breed) {
        super (String.format("Dog with breed: %s is not found",breed));
    }
}
