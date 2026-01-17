package com.infoera.sportshub_inventory.service;

import com.infoera.sportshub_inventory.model.ItemMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    /**
     * Creates a new item after validating company ID and checking for duplicate codes.
     */
    ItemMaster createItem(ItemMaster item);

    /**
     * Updates an existing item for a specific company.
     */
    ItemMaster updateItem(String id, String companyId, ItemMaster item);

    /**
     * Retrieves a specific item by ID and company ID.
     */
    ItemMaster getItemById(String id, String companyId);

    /**
     * Returns a paginated list of all active items for a specific company.
     */
    Page<ItemMaster> getAllItems(String companyId, Pageable pageable);

    /**
     * Searches for items based on a keyword within a specific company.
     */
    Page<ItemMaster> searchItems(String companyId, String keyword, Pageable pageable);

    /**
     * Performs a soft delete by setting the 'active' status to false.
     */
    void deleteItem(String id, String companyId);
}