package com.infoera.sportshub_inventory.repository;

import com.infoera.sportshub_inventory.model.UnitController;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository
        extends MongoRepository<UnitController, String> {

    boolean existsByCode(String code);
}
