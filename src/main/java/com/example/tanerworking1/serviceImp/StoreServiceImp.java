package com.example.tanerworking1.serviceImp;


import com.example.tanerworking1.model.dto.StoreDTO;
import com.example.tanerworking1.repository.StoreRepository;
import com.example.tanerworking1.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImp implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<StoreDTO> getStore() {
        List<StoreDTO> storeDTOS = new ArrayList<>();
        storeRepository.findAll().forEach(storeEntity -> {
            StoreDTO storeDTO = new StoreDTO();
            storeDTO.setId(storeEntity.getId());
            storeDTO.setName(storeEntity.getName());
            storeDTO.setEmail(storeEntity.getEmail());
            storeDTOS.add(storeDTO);

        });
        return storeDTOS;
    }
}
