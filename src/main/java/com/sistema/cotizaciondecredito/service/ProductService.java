package com.sistema.cotizaciondecredito.service;

import com.sistema.cotizaciondecredito.model.Product;
import com.sistema.cotizaciondecredito.repositories.ProductRepository;
import jakarta.persistence.Lob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    public List<Product> searchProducts(String query){
        return productRepository.findBySkuContainingOrNameContaining(query,query);
    }

    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        existingProduct.setSku(updatedProduct.getSku());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }
}
