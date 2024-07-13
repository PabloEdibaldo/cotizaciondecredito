package com.sistema.cotizaciondecredito.service;

import com.sistema.cotizaciondecredito.model.Rate;
import com.sistema.cotizaciondecredito.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;

    public Rate saveRate(Rate rate) {
        return rateRepository.save(rate);
    }

    public List<Rate> getAllRate() {
        return rateRepository.findAll();
    }

    public void deleteRate(Long id) {
        rateRepository.deleteById(id);
    }
}
