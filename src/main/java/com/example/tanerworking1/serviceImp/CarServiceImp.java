package com.example.tanerworking1.serviceImp;


import com.example.tanerworking1.model.dto.CarDTO;
import com.example.tanerworking1.model.dto.PurchaseAccount;
import com.example.tanerworking1.model.entity.CarEntity;
import com.example.tanerworking1.repository.CarRepository;
import com.example.tanerworking1.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImp implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public CarEntity saveCar(CarDTO carDTO) {
        CarEntity carEntity = new CarEntity();
        carEntity.setBrand(carDTO.getBrand());
        carEntity.setModel(carDTO.getModel());
        carEntity.setPurchaseYear(carDTO.getPurchaseYear());
        carRepository.save(carEntity);
        return carEntity;
    }

    @Override
    public List<PurchaseAccount> getPurchaseAccountWithBrand() {
        return this.carRepository.getPurchaseAccount();
    }

}
