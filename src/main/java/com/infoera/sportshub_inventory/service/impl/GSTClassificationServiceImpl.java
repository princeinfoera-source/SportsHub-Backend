package com.infoera.sportshub_inventory.service.impl;

import com.infoera.sportshub_inventory.model.GSTClassification;
import com.infoera.sportshub_inventory.repository.GSTClassificationRepository;
import com.infoera.sportshub_inventory.service.GSTClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GSTClassificationServiceImpl implements GSTClassificationService {

    private final GSTClassificationRepository repository;

    @Override
    public ResponseEntity<GSTClassification> create(GSTClassification gst) {

        if (repository.existsByCode(gst.getCode())) {
            return ResponseEntity.badRequest().build();
        }

        gst.setCreatedAt(LocalDateTime.now());
        gst.setUpdatedAt(LocalDateTime.now());
        gst.setActive(true);

        return ResponseEntity.ok(repository.save(gst));
    }

    @Override
    public ResponseEntity<GSTClassification> update(String id, GSTClassification gst) {

        return repository.findById(id)
                .map(existing -> {
                    existing.setType(gst.getType());
                    existing.setCode(gst.getCode());
                    existing.setDescription(gst.getDescription());
                    existing.setGstRate(gst.getGstRate());
                    existing.setCgst(gst.getCgst());
                    existing.setSgst(gst.getSgst());
                    existing.setIgst(gst.getIgst());
                    existing.setActive(gst.getActive());
                    existing.setUpdatedAt(LocalDateTime.now());

                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<GSTClassification> getById(String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<GSTClassification>> getAll() {
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
