package com.sistema.cotizaciondecredito.controllers;

import com.sistema.cotizaciondecredito.model.Product;
import com.sistema.cotizaciondecredito.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        log.info("Product: {}",updatedProduct);

        try {
            Product updatedProductResponse = productService.updateProduct(id, updatedProduct);
            return new ResponseEntity<>(updatedProductResponse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> searchProduct(@RequestParam String query){
        return productService.searchProducts(query);
    }

}
