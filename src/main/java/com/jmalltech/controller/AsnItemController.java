package com.jmalltech.controller;

import com.jmalltech.entity.AsnItem;
import com.jmalltech.helper.ResponseHelper;
import com.jmalltech.service.AsnItemDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients/asn-items")
@CrossOrigin(origins = "http://localhost:4200")
public class AsnItemController {
    private AsnItemDomainService aIService;

    @Autowired
    public AsnItemController(AsnItemDomainService aIService) {
        this.aIService = aIService;
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getAsnItemById(@PathVariable Long id) {
        AsnItem asnItem = aIService.getById(id);
        if (asnItem != null) {
            return ResponseEntity.ok(asnItem);
        } else {
            return ResponseHelper.notFoundResponse("AsnItem not found for ID: " + id);
        }
    }

    @GetMapping("/by-asn-id/{asnId}")
    public ResponseEntity<?> getAsnItemListByAsnId(@PathVariable Long asnId) {
        return ResponseEntity.ok(aIService.getAsnItemListFromAsnId(asnId));
    }

    @PostMapping("/")
    public ResponseEntity<AsnItem> insertAsnItem(@RequestBody AsnItem asnItem) {
        AsnItem createdAsnItem = aIService.insert(asnItem);
        return ResponseEntity.ok(createdAsnItem);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateAsnItem(@RequestBody AsnItem asnItem) {
        AsnItem updatedAsnItem = aIService.update(asnItem);
        if (updatedAsnItem != null) {
            return ResponseEntity.ok(updatedAsnItem);
        } else {
            return ResponseHelper.badRequestResponse("AsnItem not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsnItem(@PathVariable Long id) {
        boolean deleted = aIService.remove(id);
        if (deleted) {
            return ResponseEntity.ok("AsnItem deleted successfully.");
        } else {
            return ResponseHelper.badRequestResponse("AsnItem not found.");
        }
    }

    @DeleteMapping("/by-asn-id/{asnId}")
    public ResponseEntity<?> deleteAsnItemsByAsnId(@PathVariable Long asnId) {
        boolean deleted = aIService.removeAsnItemsByAsnId(asnId);
        if (deleted) {
            return ResponseEntity.ok("AsnItems deleted successfully.");
        } else {
            return ResponseHelper.badRequestResponse("AsnItems not found.");
        }
    }
}
