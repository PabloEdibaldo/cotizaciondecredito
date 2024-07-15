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

/**
 * Controlador REST para manejar operaciones relacionadas con productos.
 * Proporciona endpoints para crear, leer, actualizar, eliminar y buscar productos.
 */
@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    /**
     * Crea un nuevo producto.
     *
     * @param product El producto a crear
     * @return El producto creado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    /**
     * Obtiene todos los productos.
     *
     * @return Lista de todos los productos
     */
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id ID del producto a eliminar
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id ID del producto a actualizar
     * @param updatedProduct Datos actualizados del producto
     * @return ResponseEntity con el producto actualizado o NOT_FOUND si no se encuentra
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        log.info("Product: {}", updatedProduct);

        try {
            Product updatedProductResponse = productService.updateProduct(id, updatedProduct);
            return new ResponseEntity<>(updatedProductResponse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Busca productos basados en una consulta.
     *
     * @param query Término de búsqueda
     * @return Lista de productos que coinciden con la consulta
     */
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> searchProduct(@RequestParam String query){
        return productService.searchProducts(query);
    }
}