package com.example.tanerworking1.model.dto;

public class PurchaseAccount {

    private Long count;

    private String brandModel;

    public PurchaseAccount(){

    }

    public PurchaseAccount(Long count, String brandModel){
        this.count = count;
        this.brandModel = brandModel;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getBrand_model() {
        return brandModel;
    }

    public void setBrand_model(String brand_model) {
        this.brandModel = brand_model;
    }
}
