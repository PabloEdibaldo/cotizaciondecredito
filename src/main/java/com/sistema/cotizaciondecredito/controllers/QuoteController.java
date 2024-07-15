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

/**
 * Controlador REST para manejar las cotizaciones de crédito.
 * Proporciona un endpoint para calcular las cuotas de pago basadas en un producto y una tasa.
 */
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

    /**
     * Calcula una cotización basada en un producto y una tasa.
     *
     * @param productId ID del producto
     * @param rateId ID de la tasa
     * @return ResponseEntity con los resultados de la cotización o un mensaje de error
     */
    @GetMapping
    public ResponseEntity<?> quote(@RequestParam Long productId, @RequestParam Long rateId) {
        // Busca el producto por ID
        Product product = productService
                .getAllProducts()
                .stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);

        // Busca la tasa por ID
        Rate rate = rateService
                .getAllRate()
                .stream()
                .filter(p -> p.getId().equals(rateId))
                .findFirst()
                .orElse(null);

        // Verifica si se encontraron el producto y la tasa
        if (product == null || rate == null) {
            return ResponseEntity.badRequest().body("Producto o Plazo no encontrado");
        }

        // Calcula el pago normal
        BigDecimal abonoNormal = quotationService.calculateNormalPayment(
                product.getPrice(),
                rate.getNormalRate(),
                rate.getWeeks());

        // Calcula el pago puntual
        BigDecimal abonoPuntual = quotationService.calculateTimelyPayment(
                product.getPrice(),
                rate.getSpotRate(),
                rate.getWeeks());

        // Prepara el resultado
        Map<String, BigDecimal> resultado = new HashMap<>();
        resultado.put("abonoNormal", abonoNormal);
        resultado.put("abonoPuntual", abonoPuntual);

        // Retorna el resultado
        return ResponseEntity.ok(resultado);
    }
}