package com.infoera.sportshub_inventory.repository;

import com.infoera.sportshub_inventory.model.GSTClassification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GSTClassificationRepository
        extends MongoRepository<GSTClassification, String> {

    boolean existsByCode(String code);
}
