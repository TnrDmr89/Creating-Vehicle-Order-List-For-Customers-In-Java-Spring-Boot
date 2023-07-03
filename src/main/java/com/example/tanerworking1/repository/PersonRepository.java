package com.example.tanerworking1.repository;


import com.example.tanerworking1.model.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity,Long>{
    PersonEntity findPersonEntityByPersonTC(String oldTC);
}
