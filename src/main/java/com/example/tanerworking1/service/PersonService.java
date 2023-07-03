package com.example.tanerworking1.service;


import com.example.tanerworking1.model.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    void addPerson(PersonDTO personDTO);
    void addPersons(List<PersonDTO> personDTOList);

    List<PersonDTO> getPersons();
    void updateTC(String oldTC, String newTC);
    PersonDTO getPersonById(Long id);
    void deletePerson(Long id);
}
