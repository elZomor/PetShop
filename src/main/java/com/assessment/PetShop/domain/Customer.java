package com.assessment.PetShop.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Integer id ;

    @Column
    private String name ;

    @Column
    private String email ;

// Set FetchType to (Eager) to retrieve the orders by the customer once initialized
    @OneToMany (fetch = FetchType.EAGER , cascade=CascadeType.ALL , mappedBy = "customer")
    private List<Order> orderList ;

    public Customer(){}
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
