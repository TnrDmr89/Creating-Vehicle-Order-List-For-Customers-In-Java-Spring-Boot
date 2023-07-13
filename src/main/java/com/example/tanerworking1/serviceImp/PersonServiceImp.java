package com.example.tanerworking1.serviceImp;


import com.example.tanerworking1.exceptions.DataNotFoundInDB;
import com.example.tanerworking1.exceptions.DataNotSend;
import com.example.tanerworking1.model.dto.CarDTO;
import com.example.tanerworking1.model.dto.DealerDTO;
import com.example.tanerworking1.model.dto.PersonDTO;
import com.example.tanerworking1.model.dto.StoreDTO;
import com.example.tanerworking1.model.entity.CarEntity;
import com.example.tanerworking1.model.entity.DealerEntity;
import com.example.tanerworking1.model.entity.PersonEntity;
import com.example.tanerworking1.model.entity.StoreEntity;
import com.example.tanerworking1.repository.CarRepository;
import com.example.tanerworking1.repository.DealerRepository;
import com.example.tanerworking1.repository.PersonRepository;
import com.example.tanerworking1.repository.StoreRepository;
import com.example.tanerworking1.service.CarService;
import com.example.tanerworking1.service.PersonService;
import com.example.tanerworking1.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonServiceImp implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    @Transactional
    public void addPerson(PersonDTO personDTO) {
        try{
            PersonEntity personEntity = new PersonEntity();
            CarEntity carEntity = new CarEntity();
            DealerEntity dealerEntity = new DealerEntity();
            personEntity.setPersonTC(personDTO.getPersonTC());
            personEntity.setPersonAge(personDTO.getPersonAge());
            personEntity.setPersonName(personDTO.getPersonName());
            personEntity.setPersonSurname(personDTO.getPersonSurname());
            carEntity.setBrand(personDTO.getCar().getBrand());
            carEntity.setModel(personDTO.getCar().getModel());
            carEntity.setPurchaseYear(personDTO.getCar().getPurchaseYear());
            dealerEntity.setDealerPhone(personDTO.getCar().getDealer().getDealerPhone());
            dealerEntity.setDealerName(personDTO.getCar().getDealer().getDealerName());
            dealerEntity.setDealerAddress(personDTO.getCar().getDealer().getDealerAddress());
            dealerEntity.setId(personDTO.getCar().getDealer().getId());
            carEntity.setDealerEntity(dealerEntity);
            personEntity.setOwnerCar(carEntity);
            this.personRepository.save(personEntity);
        }catch(NullPointerException ex){
            throw new DataNotSend("601","data sent cannot be null");
        }

    }

    @Override
    @Transactional
    public void addPersons(List<PersonDTO> personDTOList) {

    }

    @Override
    public List<PersonDTO> getPersons() {
        List<PersonDTO> personDTOS = new ArrayList<>();
        this.personRepository.findAll().forEach(personEntity -> {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setPersonName(personEntity.getPersonName());
            personDTO.setPersonSurname(personEntity.getPersonSurname());
            personDTO.setPersonAge(personEntity.getPersonAge());
            personDTO.setPersonTC(personEntity.getPersonTC());
            CarDTO carDTO = new CarDTO();
            carDTO.setBrand(personEntity.getOwnerCar().getBrand());
            DealerDTO dealerDTO = new DealerDTO();
            dealerDTO.setDealerName(personEntity.getOwnerCar().getDealerEntity().getDealerName());
            carDTO.setDealer(dealerDTO);
            personDTO.setCar(carDTO);
            personDTOS.add(personDTO);
        });
        return personDTOS;
    }


    @Override
    public void updateTC(String oldTC,String newTC) {
      PersonEntity personEntity= this.personRepository.findPersonEntityByPersonTC(oldTC);
        personEntity.setPersonTC(newTC);
        this.personRepository.save(personEntity);
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        try{
            PersonDTO personDTO = new PersonDTO();
            CarDTO carDTO = new CarDTO();
            DealerDTO dealerDTO = new DealerDTO();
            StoreDTO storeDTO = new StoreDTO();
            PersonEntity personEntity = this.personRepository.findById(id).get();
            personDTO.setPersonName(personEntity.getPersonName());
            personDTO.setPersonSurname(personEntity.getPersonSurname());
            personDTO.setPersonTC(personEntity.getPersonTC());
            personDTO.setPersonAge(personEntity.getPersonAge());
            CarEntity carEntity = this.carRepository.findById(personEntity.getId()).get();
            carDTO.setBrand(carEntity.getBrand());
            carDTO.setModel(carEntity.getModel());
            carDTO.setPurchaseYear(carEntity.getPurchaseYear());
            DealerEntity dealerEntity = this.dealerRepository.findById(carEntity.getId()).get();
            dealerDTO.setDealerName(dealerEntity.getDealerName());
            dealerDTO.setDealerPhone(dealerEntity.getDealerPhone());
            dealerDTO.setDealerAddress(dealerEntity.getDealerAddress());
            dealerDTO.setId(dealerEntity.getId());
            StoreEntity storeEntity = this.storeRepository.findById(dealerEntity.getId()).get();
            storeDTO.setName(storeEntity.getName());
            storeDTO.setEmail(storeEntity.getEmail());
            storeDTO.setId(storeEntity.getId());
            dealerDTO.setStore(storeDTO);
            carDTO.setDealer(dealerDTO);
            personDTO.setCar(carDTO);
            return personDTO;
        }catch(NoSuchElementException ex){
            throw new DataNotFoundInDB("600","Customer is not found in DB");
        }
    }

    @Override
    public void deletePerson(String tc) {
        try{
            PersonEntity personEntity = this.personRepository.findPersonEntityByPersonTC(tc);
            this.personRepository.delete(personEntity);
        }catch(Exception e){
            throw new DataNotFoundInDB("600","Customer is not found in DB");
        }

    }
}
