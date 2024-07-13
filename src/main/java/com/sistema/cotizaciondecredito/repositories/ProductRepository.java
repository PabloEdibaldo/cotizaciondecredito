package com.sistema.cotizaciondecredito.repositories;

import com.sistema.cotizaciondecredito.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findBySkuContainingOrNameContaining(String sku, String name);
}
