package com.assessment.PetShop.domain;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue
    private Integer id ;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "dogId")
    private Dog dog ;

    @Column
    private int quantity ;

    public Inventory(){}
    public Inventory(Dog dog, int quantity) {
        this.dog = dog;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
