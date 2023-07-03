package com.example.tanerworking1.service;


import com.example.tanerworking1.model.dto.CarDTO;
import com.example.tanerworking1.model.dto.PurchaseAccount;
import com.example.tanerworking1.model.entity.CarEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarService {
    CarEntity saveCar(CarDTO carDTO);
@Query(value=" select new com.example.tanerworking1.model.dto.PurchaseAccount(count(car),car.brand) from CarEntity as car group by car.brand")
List<PurchaseAccount> getPurchaseAccountWithBrand();
}
