package com.assessment.PetShop.domain;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Integer id ;

    @ManyToOne
    @Column
    private Order order ;

    @ManyToOne
    @Column
    private Customer customer ;

    @ManyToOne
    @Column
    private Dog dog ;

    @Column
    private double price ;

    @Column
    private String currency ;

    public OrderItem(){}
    public OrderItem(Order order, Customer customer, Dog dog, double price, String currency) {
        this.order = order;
        this.customer = customer;
        this.dog = dog;
        this.price = price;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
