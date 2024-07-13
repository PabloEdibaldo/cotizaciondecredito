package com.sistema.cotizaciondecredito.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "rate_t")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int weeks;
    private BigDecimal normalRate;
    private BigDecimal spotRate;
}
