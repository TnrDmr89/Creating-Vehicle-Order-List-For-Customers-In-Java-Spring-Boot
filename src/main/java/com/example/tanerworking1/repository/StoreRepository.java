package com.example.tanerworking1.repository;

import com.example.tanerworking1.model.entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<StoreEntity,Long> {
}
