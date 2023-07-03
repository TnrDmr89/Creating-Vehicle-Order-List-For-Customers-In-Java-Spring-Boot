package com.example.tanerworking1.model.dto;

import lombok.Data;

@Data
public class CarDTO {

    private String brand;
    private String model;
    private String purchaseYear;
    private DealerDTO dealer;

}
