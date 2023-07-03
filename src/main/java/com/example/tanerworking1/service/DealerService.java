package com.example.tanerworking1.service;


import com.example.tanerworking1.model.dto.DealerDTO;

import java.util.List;

public interface DealerService {

    void addDealer(DealerDTO dealerDTO);

    List<DealerDTO> getDealerByStore(Long id);
}
