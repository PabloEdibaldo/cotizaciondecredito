package com.sistema.cotizaciondecredito.controllers;

import com.sistema.cotizaciondecredito.model.Product;
import com.sistema.cotizaciondecredito.model.Rate;
import com.sistema.cotizaciondecredito.service.ProductService;
import com.sistema.cotizaciondecredito.service.QuotationService;
import com.sistema.cotizaciondecredito.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/quote")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class QuoteController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final RateService rateService;
    @Autowired
    private final QuotationService quotationService;

    @GetMapping
    public ResponseEntity<?> quote(@RequestParam Long productId, @RequestParam Long rateId) {
        Product product = productService
                .getAllProducts()
                .stream().
                filter(p -> p.getId()
                        .equals(productId))
                .findFirst()
                .orElse(null);
        Rate rate = rateService
                .getAllRate()
                .stream()
                .filter(p -> p.getId()
                        .equals(rateId))
                .findFirst().
                orElse(null);

        if (product == null || rate == null) {
            return ResponseEntity.badRequest().body("Producto o Plazo no encontrado");
        }

        BigDecimal abonoNormal = quotationService.calculateNormalPayment(
                product.getPrice(),
                rate.getNormalRate(),
                rate.getWeeks());

        BigDecimal abonoPuntual = quotationService.calculateTimelyPayment(
                product.getPrice(),
                rate.getSpotRate(),
                rate.getWeeks());

        Map<String, BigDecimal> resultado = new HashMap<>();
        resultado.put("abonoNormal", abonoNormal);
        resultado.put("abonoPuntual", abonoPuntual);

        return ResponseEntity.ok(resultado);
    }
}
