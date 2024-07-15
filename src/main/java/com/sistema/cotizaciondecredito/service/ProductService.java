package com.sistema.cotizaciondecredito.service;

import com.sistema.cotizaciondecredito.model.Product;
import com.sistema.cotizaciondecredito.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que maneja las operaciones relacionadas con los productos.
 * Proporciona métodos para crear, leer, actualizar, eliminar y buscar productos.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * Guarda un nuevo producto o actualiza uno existente.
     *
     * @param product El producto a guardar
     * @return El producto guardado
     */
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    /**
     * Obtiene todos los productos.
     *
     * @return Lista de todos los productos
     */
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id ID del producto a eliminar
     */
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    /**
     * Busca productos por SKU o nombre.
     *
     * @param query Término de búsqueda
     * @return Lista de productos que coinciden con la consulta
     */
    public List<Product> searchProducts(String query){
        return productRepository.findBySkuContainingOrNameContaining(query, query);
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id ID del producto a actualizar
     * @param updatedProduct Datos actualizados del producto
     * @return El producto actualizado
     * @throws RuntimeException si el producto no se encuentra
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        existingProduct.setSku(updatedProduct.getSku());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }
}