package com.infoera.sportshub_inventory.repository;

import com.infoera.sportshub_inventory.model.ItemMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<ItemMaster, String> {

    // Check if code exists WITHIN this company (Fixes the duplicate bug)
    boolean existsByCompanyIdAndCode(String companyId, String code);

    // Find active items for a SPECIFIC company
    Page<ItemMaster> findByCompanyIdAndActiveTrue(String companyId, Pageable pageable);

    // CRITICAL: Search restricted to Company ID
    @Query("{ 'companyId': ?0, 'active': true, $or: [ { 'name': { $regex: ?1, $options: 'i' } }, { 'code': { $regex: ?1, $options: 'i' } } ] }")
    Page<ItemMaster> searchByCompanyAndKeyword(String companyId, String keyword, Pageable pageable);
    
    // Find single item securely
    Optional<ItemMaster> findByIdAndCompanyId(String id, String companyId);
}