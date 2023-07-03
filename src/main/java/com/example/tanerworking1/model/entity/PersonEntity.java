package com.example.tanerworking1.model.entity;


import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "persontable")
@NoArgsConstructor
public class PersonEntity extends BaseEntity {
    @Column(name = "name")
    private String personName;
    @Column(name="surname")
    private String personSurname;
    @Column(name="age")
    private int personAge;
    @Column(name="tc")
    private String personTC;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="car_id")
    private CarEntity ownerCar;


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

    public String getPersonTC() {
        return personTC;
    }

    public void setPersonTC(String personTC) {
        this.personTC = personTC;
    }

    public CarEntity getOwnerCar() {
        return ownerCar;
    }

    public void setOwnerCar(CarEntity ownerCar) {
        this.ownerCar = ownerCar;
    }
}
