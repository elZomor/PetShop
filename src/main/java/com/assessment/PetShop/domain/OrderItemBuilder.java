package com.assessment.PetShop.domain;

public class OrderItemBuilder {
    private Customer customer;
    private Order order ;
    private Dog dog ;
    private String currency ;
    private double price ;

    public OrderItemBuilder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public OrderItemBuilder setDog(Dog dog) {
        this.dog = dog;
        return this;
    }
    public OrderItemBuilder setOrder(Order order) {
        this.order = order;
        return this;
    }
    public OrderItemBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
    public OrderItemBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public OrderItem createOrderItem() {

        return new OrderItem(order,customer,dog,price,currency);
    }
}