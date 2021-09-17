package com.assessment.PetShop.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue
    private Integer id ;

    @ManyToOne(cascade=CascadeType.ALL)
    private Customer customer ;

    @CreatedDate
    @Column
    private Instant createdAt ;

    public Order(){}
    public Order(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {this.id = id ;}
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
