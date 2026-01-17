package com.infoera.sportshub_inventory.repository;

import com.infoera.sportshub_inventory.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByIdAndCompanyId(String id, String companyId);

    List<User> findByCompanyIdAndActiveTrue(String companyId);

    Optional<User> findByEmailAndCompanyId(String email, String companyId);

    boolean existsByEmailAndCompanyId(String email, String companyId);
}