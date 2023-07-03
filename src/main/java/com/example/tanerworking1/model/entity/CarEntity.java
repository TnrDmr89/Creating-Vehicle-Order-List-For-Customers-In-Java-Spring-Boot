package com.example.tanerworking1.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
public class CarEntity extends BaseEntity{
    @Column(name="brand")
    private String brand;
    @Column(name="model")
    private String model;
    @Column(name="purchase_year")
    private String purchaseYear;
    @OneToOne(mappedBy = "ownerCar")
    private PersonEntity person;
    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerEntity dealerEntity;



}
