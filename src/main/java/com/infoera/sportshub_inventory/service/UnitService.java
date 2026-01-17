package com.infoera.sportshub_inventory.service;

import com.infoera.sportshub_inventory.model.UnitController;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UnitService {

    ResponseEntity<UnitController> create(UnitController unit);

    ResponseEntity<UnitController> update(String id, UnitController unit);

    ResponseEntity<UnitController> getById(String id);

    ResponseEntity<List<UnitController>> getAll();

    ResponseEntity<Void> delete(String id);
}
