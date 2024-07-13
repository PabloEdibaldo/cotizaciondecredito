package com.sistema.cotizaciondecredito.controllers;


import com.sistema.cotizaciondecredito.model.Rate;
import com.sistema.cotizaciondecredito.service.RateService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RateController {
    @Autowired
    private final RateService rateService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Rate addRate(@RequestBody Rate rate) {
        return rateService.saveRate(rate);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Rate> getAllRates() {
        return rateService.getAllRate();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRate(@PathVariable Long id) {
        rateService.deleteRate(id);
    }
}
