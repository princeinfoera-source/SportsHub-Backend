package com.infoera.sportshub_inventory.repository;

import com.infoera.sportshub_inventory.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
}