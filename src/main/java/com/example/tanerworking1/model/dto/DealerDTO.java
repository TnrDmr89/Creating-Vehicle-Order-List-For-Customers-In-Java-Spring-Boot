package com.example.tanerworking1.model.dto;

import lombok.Data;

@Data
public class DealerDTO {

    private String dealerName;

    private String dealerAddress;

    private String dealerPhone;

    private Long id;

    private StoreDTO store;
}
