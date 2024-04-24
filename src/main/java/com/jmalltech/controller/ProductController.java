package com.jmalltech.controller;

import com.jmalltech.entity.Product;
import com.jmalltech.helper.ResponseHelper;
import com.jmalltech.service.ProductDomainService;
import com.jmalltech.web.annotation.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private ProductDomainService pDService;

    @Autowired
    public ProductController(ProductDomainService pDService) {
        this.pDService = pDService;
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id, @ClientId Long clientId) {
        Product product = pDService.getByIdAndClientId(id, clientId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseHelper.notFoundResponse("Product not found for ID: " + id);
        }
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name, @ClientId Long clientId) {
        Product product = pDService.getByNameAndClientId(name, clientId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseHelper.notFoundResponse("Product not found for name: " + name);
        }
    }

    @GetMapping("/by-sku/{sku}")
    public ResponseEntity<?> getProductBySku(@PathVariable String sku, @ClientId Long clientId) {
        Product product = pDService.getBySkuAndClientId(sku, clientId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseHelper.notFoundResponse("Product not found for sku: " + sku);
        }
    }

    @GetMapping("/my-products-list")
    public ResponseEntity<?> getMyProductsList(@ClientId Long clientId) {
        try {
            List<Product> list = pDService.getProductListByClientId(clientId);
            if (!list.isEmpty()) {
                return ResponseEntity.ok(list);
            } else {
                return ResponseHelper.notFoundResponse("No products found for client ID: " + clientId);
            }
        } catch (SecurityException e) {
            return ResponseHelper.badRequestResponse(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> insertProduct(@RequestBody Product product, @ClientId Long clientId) {
        product.setClientId(clientId);
        Product createdProduct = pDService.insert(product);
        return ResponseEntity.ok(createdProduct);
    }
    /*@PostMapping("/")
    public ResponseEntity<?> insertProduct(@RequestBody Product product){
        Product createdProduct = pDService.insert(product);
        return ResponseEntity.ok(createdProduct);
    }*/

    @PutMapping("/")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        System.out.println(product);
        Product updatedProduct = pDService.update(product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseHelper.badRequestResponse("Product not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean isRemoved = pDService.remove(id);
        if (isRemoved) {
            return ResponseEntity.ok().body("{\"message\": \"Product removed successfully.\"}");
        } else {
            return ResponseHelper.notFoundResponse("Product not found for ID: " + id);
        }
    }
}
