package com.example.tanerworking1.repository;


import com.example.tanerworking1.model.dto.PurchaseAccount;
import com.example.tanerworking1.model.entity.CarEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<CarEntity,Long>{


    @Query(value = "select new com.example.tanerworking1.model.dto.PurchaseAccount(count(cr),cr.brand) from CarEntity as cr  " +
            "group by cr.brand")
    List<PurchaseAccount> getPurchaseAccount();

}
