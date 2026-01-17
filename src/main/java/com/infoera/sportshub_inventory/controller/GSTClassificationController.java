package com.infoera.sportshub_inventory.controller;

import com.infoera.sportshub_inventory.model.GSTClassification;
import com.infoera.sportshub_inventory.service.GSTClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gst-classifications")
@RequiredArgsConstructor
public class GSTClassificationController {

    private final GSTClassificationService service;

    @PostMapping
    public ResponseEntity<GSTClassification> create(
            @RequestBody GSTClassification gst) {
        return service.create(gst);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GSTClassification> update(
            @PathVariable String id,
            @RequestBody GSTClassification gst) {
        return service.update(id, gst);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GSTClassification> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<GSTClassification>> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
