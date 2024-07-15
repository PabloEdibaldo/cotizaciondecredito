package com.sistema.cotizaciondecredito.controllers;

import com.sistema.cotizaciondecredito.model.Rate;
import com.sistema.cotizaciondecredito.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar operaciones relacionadas con las tasas (Rate).
 * Proporciona endpoints para crear, leer y eliminar tasas.
 */
@RestController
@RequestMapping("/rate")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RateController {

    @Autowired
    private final RateService rateService;

    /**
     * Crea una nueva tasa.
     *
     * @param rate La tasa a crear
     * @return La tasa creada
     */
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Rate addRate(@RequestBody Rate rate) {
        return rateService.saveRate(rate);
    }

    /**
     * Obtiene todas las tasas.
     *
     * @return Lista de todas las tasas
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Rate> getAllRates() {
        return rateService.getAllRate();
    }

    /**
     * Elimina una tasa por su ID.
     *
     * @param id ID de la tasa a eliminar
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRate(@PathVariable Long id) {
        rateService.deleteRate(id);
    }
}