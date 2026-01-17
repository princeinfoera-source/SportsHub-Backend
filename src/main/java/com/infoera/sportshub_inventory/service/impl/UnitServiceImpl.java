package com.infoera.sportshub_inventory.service.impl;

import com.infoera.sportshub_inventory.model.UnitController;
import com.infoera.sportshub_inventory.repository.UnitRepository;
import com.infoera.sportshub_inventory.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository repository;

    @Override
    public ResponseEntity<UnitController> create(UnitController unit) {

        if (repository.existsByCode(unit.getCode())) {
            return ResponseEntity.badRequest().build();
        }

        unit.setCreatedAt(LocalDateTime.now());
        unit.setUpdatedAt(LocalDateTime.now());
        unit.setActive(true);

        return ResponseEntity.ok(repository.save(unit));
    }

    @Override
    public ResponseEntity<UnitController> update(String id, UnitController unit) {

        return repository.findById(id)
                .map(existing -> {
                    existing.setCode(unit.getCode());
                    existing.setName(unit.getName());
                    existing.setDecimalPlaces(unit.getDecimalPlaces());
                    existing.setUqc(unit.getUqc());
                    existing.setActive(unit.getActive());
                    existing.setUpdatedAt(LocalDateTime.now());

                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UnitController> getById(String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<UnitController>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
