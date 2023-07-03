package com.example.tanerworking1.controller;


import com.example.tanerworking1.model.dto.DealerDTO;
import com.example.tanerworking1.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dealer")
@CrossOrigin("http://localhost:8083")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @PostMapping("/addDealer")
    public ResponseEntity addDealer(@RequestBody DealerDTO dealerDTO){
        this.dealerService.addDealer(dealerDTO);
        return new ResponseEntity("BASARILI", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getDealerByStore")
    public List<DealerDTO> getDealerByStore(@RequestParam(name="id") Long id){
        return this.dealerService.getDealerByStore(id);
     }
}
