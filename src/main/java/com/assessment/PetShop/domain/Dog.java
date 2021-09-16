package com.assessment.PetShop.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Dog {
    @Id
    @GeneratedValue
    private Integer id ;

    @Column
    @NotNull
    private String breed ;

    @Column
    @NotNull
    private Character sex ;

    public Dog(){}
    public Dog(String breed, char sex) {
        this.breed = breed;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }
}
