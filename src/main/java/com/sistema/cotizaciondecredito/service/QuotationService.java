package com.sistema.cotizaciondecredito.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class QuotationService {

    public BigDecimal calculateNormalPayment(
            BigDecimal price,
            BigDecimal normalRate,
            int term) {
        return price.multiply(normalRate)
                .add(price)
                .divide(BigDecimal
                        .valueOf(term), RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTimelyPayment(
            BigDecimal price,
            BigDecimal spotRate,
            int term) {
        return price.multiply(spotRate)
                .add(price)
                .divide(BigDecimal
                        .valueOf(term), RoundingMode.HALF_UP);
    }
}
