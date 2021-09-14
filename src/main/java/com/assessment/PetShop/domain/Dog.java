package com.assessment.PetShop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dog {
    @Id
    @GeneratedValue
    private Integer id ;

    @Column
    private String breed ;

    @Column
    private double age ;

    @Column
    private char sex ;

    public Dog(){}
    public Dog(String breed, double age, char sex) {
        this.breed = breed;
        this.age = age;
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

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
