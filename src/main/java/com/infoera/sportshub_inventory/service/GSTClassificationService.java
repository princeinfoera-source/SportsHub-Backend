package com.infoera.sportshub_inventory.service;

import com.infoera.sportshub_inventory.model.GSTClassification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GSTClassificationService {

    ResponseEntity<GSTClassification> create(GSTClassification gst);

    ResponseEntity<GSTClassification> update(String id, GSTClassification gst);

    ResponseEntity<GSTClassification> getById(String id);

    ResponseEntity<List<GSTClassification>> getAll();

    ResponseEntity<Void> delete(String id);
}
