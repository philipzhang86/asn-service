package com.jmalltech.controller;

import com.jmalltech.entity.Product;
import com.jmalltech.helper.ResponseHelper;
import com.jmalltech.service.JwtTokenService;
import com.jmalltech.service.ProductDomainService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private ProductDomainService pDService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public ProductController(ProductDomainService pDService, JwtTokenService jwtTokenService) {
        this.pDService = pDService;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = pDService.getById(id);
        if(product != null){
            return ResponseEntity.ok(product);
        }else {
            return ResponseHelper.notFoundResponse("Product not found for ID: " + id);
        }

    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name) {
        Product product = pDService.getByProductName(name);
        if(product != null){
            return ResponseEntity.ok(product);
        }else {
            return ResponseHelper.notFoundResponse("Product not found for name: " + name);
        }
    }

    @GetMapping("/by-sku/{sku}")
    public ResponseEntity<?> getProductBySku(@PathVariable String sku) {
        Product product = pDService.getBySku(sku);
        if(product != null){
            return ResponseEntity.ok(product);
        }else {
            return ResponseHelper.notFoundResponse("Product not found for sku: " + sku);
        }
    }

    @GetMapping("/my-products-list")
    public ResponseEntity<?> getMyProductsList(HttpServletRequest request) {
        try {
            Long clientId = jwtTokenService.getClientIdIfClient(request);
            return ResponseEntity.ok(pDService.getProductListByClientId(clientId));
        } catch (SecurityException e) {
            return ResponseHelper.badRequestResponse(e.getMessage());
        }
    }
}
