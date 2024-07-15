package com.sistema.cotizaciondecredito.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Servicio que proporciona cálculos de cuotas para cotizaciones de crédito.
 */
@Service
public class QuotationService {

    /**
     * Calcula el pago normal (cuota) basado en el precio, tasa normal y plazo.
     *
     * @param price El precio del producto
     * @param normalRate La tasa normal de interés
     * @param term El plazo en número de pagos
     * @return La cuota normal calculada
     */
    public BigDecimal calculateNormalPayment(
            BigDecimal price,
            BigDecimal normalRate,
            int term) {
        return price.multiply(normalRate)
                .add(price)
                .divide(BigDecimal.valueOf(term), RoundingMode.HALF_UP);
    }

    /**
     * Calcula el pago puntual (cuota) basado en el precio, tasa puntual y plazo.
     *
     * @param price El precio del producto
     * @param spotRate La tasa puntual de interés
     * @param term El plazo en número de pagos
     * @return La cuota puntual calculada
     */
    public BigDecimal calculateTimelyPayment(
            BigDecimal price,
            BigDecimal spotRate,
            int term) {
        return price.multiply(spotRate)
                .add(price)
                .divide(BigDecimal.valueOf(term), RoundingMode.HALF_UP);
    }
}