package com.example.tanerworking1.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="dealer")
@Data
public class DealerEntity extends BaseEntity {

    private String dealerName;

    private String dealerAddress;

    private String dealerPhone;

    @ManyToOne
    @JoinColumn(name="store_id")
    private StoreEntity storeEntity;


}
