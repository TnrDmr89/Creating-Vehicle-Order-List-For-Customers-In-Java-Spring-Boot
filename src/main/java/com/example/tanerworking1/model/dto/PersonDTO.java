package com.example.tanerworking1.model.dto;




public class PersonDTO {

    private String personName;
    private String personSurname;
    private int personAge;
    private String personTC;

    private CarDTO car;

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

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }
}
