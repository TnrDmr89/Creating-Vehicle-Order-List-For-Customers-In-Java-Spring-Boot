package com.example.tanerworking1.controller;



import com.example.tanerworking1.model.dto.PurchaseAccount;
import com.example.tanerworking1.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin("http://localhost:8083")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/purchaseAccountWithBrand")
    public List<PurchaseAccount> getPurchaseAccountWithBrand(){
        return this.carService.getPurchaseAccountWithBrand();
    }

}
