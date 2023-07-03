package com.example.tanerworking1.repository;


import com.example.tanerworking1.model.entity.DealerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerRepository extends CrudRepository<DealerEntity,Long> {

    @Query(value="select * from dealer where store_id = :id",nativeQuery = true)
    List<DealerEntity> getDealerByStore(Long id);

}
