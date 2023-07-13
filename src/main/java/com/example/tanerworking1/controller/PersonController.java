package com.example.tanerworking1.controller;


import com.example.tanerworking1.exceptions.DataNotFoundInDB;
import com.example.tanerworking1.model.dto.PersonDTO;
import com.example.tanerworking1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin("http://localhost:8083")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/savePerson") // Tek bir kişi kaydı
    public ResponseEntity addPerson(@RequestBody PersonDTO personDTO){
        personService.addPerson(personDTO);
        return new ResponseEntity("BASARILI", HttpStatus.ACCEPTED);
    }

    @PostMapping("/savePersons") // Çoklu kişi kaydı
    public ResponseEntity addPersons(@RequestBody List<PersonDTO> personDTOList){
        personService.addPersons(personDTOList);
        return new ResponseEntity("BASARILI",HttpStatus.ACCEPTED);
    }

    @GetMapping("/allPersons") // Bütün kişi tablosunu alır
    public List<PersonDTO> allPersons(){
        return this.personService.getPersons();
    }

    @GetMapping("/getPersonById/{id}")  // Tek bir kişi bilgisini getir
    public ResponseEntity<?> getPersonById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.personService.getPersonById(id),HttpStatus.OK);
        }catch(DataNotFoundInDB db){
            return new ResponseEntity<>(db.getErrorMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateTC") // TC değişikliği
    public void updateTC(@RequestParam String oldTC, @RequestParam String newTC){
        this.personService.updateTC(oldTC,newTC);
    }

    @DeleteMapping("/deletePerson") //tc ile kişiyi tablodan sil
    public  ResponseEntity<?> deletePerson(@RequestParam String tc){
        try{
            this.personService.deletePerson(tc);
            return new ResponseEntity<>("BASARILI",HttpStatus.ACCEPTED);
        }catch(DataNotFoundInDB e){
            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }
    }

}
