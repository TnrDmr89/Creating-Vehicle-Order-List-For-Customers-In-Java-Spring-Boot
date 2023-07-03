package com.example.tanerworking1.serviceImp;


import com.example.tanerworking1.model.dto.DealerDTO;
import com.example.tanerworking1.model.entity.DealerEntity;
import com.example.tanerworking1.model.entity.StoreEntity;
import com.example.tanerworking1.repository.DealerRepository;
import com.example.tanerworking1.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealerServiceImp implements DealerService {

    @Autowired
    private DealerRepository dealerRepository;


    @Override
    public void addDealer(DealerDTO dealerDTO) {

       DealerEntity dealerEntity = new DealerEntity();
        StoreEntity storeEntity = new StoreEntity();
        dealerEntity.setDealerName(dealerDTO.getDealerName());
        dealerEntity.setDealerAddress(dealerDTO.getDealerAddress());
        dealerEntity.setDealerPhone(dealerDTO.getDealerPhone());
        storeEntity.setId(dealerDTO.getStore().getId());
        storeEntity.setName(dealerDTO.getStore().getName());
        storeEntity.setEmail(dealerDTO.getStore().getEmail());
        dealerEntity.setStoreEntity(storeEntity);
        this.dealerRepository.save(dealerEntity);



    }

    @Override
    public List<DealerDTO> getDealerByStore(Long id) {
        List<DealerDTO> dealerDTOS = new ArrayList<>();
        this.dealerRepository.getDealerByStore(id).forEach(dealerEntity -> {
            DealerDTO dealerDTO = new DealerDTO();
            dealerDTO.setDealerName(dealerEntity.getDealerName());
            dealerDTO.setDealerAddress(dealerEntity.getDealerAddress());
            dealerDTO.setDealerPhone(dealerEntity.getDealerPhone());
            dealerDTO.setId(dealerEntity.getId());
            dealerDTOS.add(dealerDTO);
        });


        return dealerDTOS;
    }


}
