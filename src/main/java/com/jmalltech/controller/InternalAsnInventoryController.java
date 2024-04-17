package com.jmalltech.controller;

import com.jmalltech.entity.Asn;
import com.jmalltech.entity.AsnItem;
import com.jmalltech.entity.Product;
import com.jmalltech.helper.ResponseHelper;
import com.jmalltech.service.AsnDomainService;
import com.jmalltech.service.AsnItemDomainService;
import com.jmalltech.service.ProductDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal")//http://localhost:9081/asn-service   url prefix
public class InternalAsnInventoryController {
    private AsnDomainService asnService;
    private AsnItemDomainService asnItemService;
    private ProductDomainService productService;
    @Autowired
    public InternalAsnInventoryController(AsnDomainService asnService, AsnItemDomainService asnItemService, ProductDomainService productService) {
        this.asnService = asnService;
        this.asnItemService = asnItemService;
        this.productService = productService;
    }

    @GetMapping("/by-asn-id/{asnId}")
    public ResponseEntity<?> getAsnById(@PathVariable Long asnId) {
        Asn asn = asnService.getById(asnId);
        if (asn != null) {
            return ResponseEntity.ok(asn);
        } else {
            return ResponseHelper.notFoundResponse("Asn not found for ID: " + asnId);
        }
    }

    @GetMapping("/by-asn-item-id/{asnItemId}")
    public ResponseEntity<?> getAsnItemById(@PathVariable Long asnItemId) {
        AsnItem asnItem = asnItemService.getById(asnItemId);
        if (asnItem != null) {
            return ResponseEntity.ok(asnItem);
        } else {
            return ResponseHelper.notFoundResponse("AsnItem not found for ID: " + asnItemId);
        }
    }

    @GetMapping("items-by-asn-id/{asnId}")
    public ResponseEntity<?> getAsnItemsByAsnId(@PathVariable Long asnId) {
        List<AsnItem> list = asnItemService.getAsnItemListFromAsnId(asnId);
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseHelper.notFoundResponse("AsnItem not found for asnId: " + asnId);
        }
    }

    @GetMapping("/by-product-id/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        Product p = productService.getById(productId);
        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseHelper.notFoundResponse("Product not found for ID: " + productId);
        }
    }

    @GetMapping("/by-product-sku/{sku}")
    public ResponseEntity<?> getProductBySku(@PathVariable String sku) {
        Product p = productService.getBySku(sku);
        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseHelper.notFoundResponse("Product not found for SKU: " + sku);
        }
    }
}
