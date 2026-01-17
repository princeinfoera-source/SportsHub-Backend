package com.infoera.sportshub_inventory.controller;

import com.infoera.sportshub_inventory.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService service;

    @PostMapping
    public ResponseEntity<com.infoera.sportshub_inventory.model.UnitController> create(
            @RequestBody com.infoera.sportshub_inventory.model.UnitController unit) {
        return service.create(unit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.infoera.sportshub_inventory.model.UnitController> update(
            @PathVariable String id,
            @RequestBody com.infoera.sportshub_inventory.model.UnitController unit) {
        return service.update(id, unit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.infoera.sportshub_inventory.model.UnitController> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<com.infoera.sportshub_inventory.model.UnitController>> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
