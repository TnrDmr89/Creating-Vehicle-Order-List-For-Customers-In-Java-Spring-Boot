package com.example.tanerworking1.serviceImp;


import com.example.tanerworking1.model.dto.CarDTO;
import com.example.tanerworking1.model.dto.DealerDTO;
import com.example.tanerworking1.model.dto.PersonDTO;
import com.example.tanerworking1.model.entity.CarEntity;
import com.example.tanerworking1.model.entity.DealerEntity;
import com.example.tanerworking1.model.entity.PersonEntity;
import com.example.tanerworking1.model.entity.StoreEntity;
import com.example.tanerworking1.repository.CarRepository;
import com.example.tanerworking1.repository.PersonRepository;
import com.example.tanerworking1.service.CarService;
import com.example.tanerworking1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImp implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Override
    @Transactional
    public void addPerson(PersonDTO personDTO) {
      PersonEntity personEntity = new PersonEntity();
      CarEntity carEntity = new CarEntity();
      DealerEntity dealerEntity = new DealerEntity();
      StoreEntity storeEntity = new StoreEntity();
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
        PersonDTO personDTO = new PersonDTO();
        PersonEntity personEntity = this.personRepository.findById(id).get();
        personDTO.setPersonName(personEntity.getPersonName());
        personDTO.setPersonSurname(personEntity.getPersonSurname());
        personDTO.setPersonTC(personEntity.getPersonTC());
        personDTO.setPersonAge(personEntity.getPersonAge());
        return personDTO;
    }

    @Override
    public void deletePerson(Long id) {
        this.personRepository.delete(this.personRepository.findById(id).get());
    }
}
