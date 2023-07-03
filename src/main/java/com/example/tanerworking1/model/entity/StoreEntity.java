package com.example.tanerworking1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="store")
public class StoreEntity extends BaseEntity{

    @Column(name="store_name",unique = true)
    private String name;

    @Column(name="store_email")
    private String email;

}
