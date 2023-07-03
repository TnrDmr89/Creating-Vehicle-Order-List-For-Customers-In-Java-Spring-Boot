package com.example.tanerworking1.controller;


import com.example.tanerworking1.model.dto.StoreDTO;
import com.example.tanerworking1.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
@CrossOrigin("http://localhost:8083")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/allStore")
    public List<StoreDTO> getStore(){
        return this.storeService.getStore();
    }
}
