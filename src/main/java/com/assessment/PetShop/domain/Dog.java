package com.assessment.PetShop.domain;

import javax.persistence.*;

@Entity
public class Dog {
    @Id
    @GeneratedValue
    private Integer id ;

    @Column
    private String breed ;

    @Column
    private char sex ;

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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
