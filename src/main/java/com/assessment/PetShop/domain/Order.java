package com.assessment.PetShop.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Integer id ;

    @ManyToOne
    private Customer customer ;

    @CreatedDate
    private LocalDateTime createdAt ;

    public Order(){}
    public Order(Customer customer, LocalDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
