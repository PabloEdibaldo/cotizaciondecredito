package com.sistema.cotizaciondecredito.service;

import com.sistema.cotizaciondecredito.model.Rate;
import com.sistema.cotizaciondecredito.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que maneja las operaciones relacionadas con las tasas (Rate).
 * Proporciona métodos para crear, leer y eliminar tasas.
 */
@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;

    /**
     * Guarda una nueva tasa o actualiza una existente.
     *
     * @param rate La tasa a guardar
     * @return La tasa guardada
     */
    public Rate saveRate(Rate rate) {
        return rateRepository.save(rate);
    }

    /**
     * Obtiene todas las tasas.
     *
     * @return Lista de todas las tasas
     */
    public List<Rate> getAllRate() {
        return rateRepository.findAll();
    }

    /**
     * Elimina una tasa por su ID.
     *
     * @param id ID de la tasa a eliminar
     */
    public void deleteRate(Long id) {
        rateRepository.deleteById(id);
    }
}