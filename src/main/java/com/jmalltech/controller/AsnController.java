package com.jmalltech.controller;

import com.jmalltech.entity.Asn;
import com.jmalltech.helper.ResponseHelper;
import com.jmalltech.service.AsnDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients/asns")
@CrossOrigin(origins = "http://localhost:4200")
public class AsnController {
    private AsnDomainService aService;

    @Autowired
    public AsnController(AsnDomainService aService) {
        this.aService = aService;
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getAsnById(@PathVariable Long id) {
        Asn asn = aService.getById(id);
        if (asn != null) {
            return ResponseEntity.ok(asn);
        } else {
            return ResponseHelper.notFoundResponse("Asn not found for ID: " + id);
        }
    }

    @GetMapping("/by-client-id/{clientId}")
    public ResponseEntity<?> getListByClientId(@PathVariable Long clientId) {
        List<Asn> list = aService.getListByClientId(clientId);
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseHelper.notFoundResponse("Asn not found for clientId: " + clientId);
        }
    }

    @GetMapping("/by-client-id-and-status/{clientId}/{status}")
    public ResponseEntity<?> getListByClientIdAndStatus(@PathVariable Long clientId, @PathVariable String status) {
        List<Asn> list = aService.getListByClientIdAndStatus(clientId, Short.valueOf(status));
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseHelper.notFoundResponse("Asn not found for clientId: " + clientId + " and status: " + status);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Asn> insertAsn(@RequestBody Asn asn) {
        Asn createdAsn = aService.insert(asn);
        return ResponseEntity.ok(createdAsn);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateAsn(@RequestBody Asn asn) {
        Asn updatedAsn = aService.update(asn);
        if (updatedAsn != null) {
            return ResponseEntity.ok(updatedAsn);
        } else {
            return ResponseHelper.badRequestResponse("Asn not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsn(@PathVariable Long id) {
        boolean deleted = aService.remove(id);
        if (deleted) {
            return ResponseEntity.ok().body("{\"message\":\"Asn deleted successfully.\"}");
        } else {
            return ResponseHelper.badRequestResponse("Asn not found.");
        }
    }
}
