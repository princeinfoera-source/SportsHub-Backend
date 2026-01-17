package com.infoera.sportshub_inventory.service.impl;

import com.infoera.sportshub_inventory.model.ItemMaster;
import com.infoera.sportshub_inventory.repository.ItemRepository;
import com.infoera.sportshub_inventory.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public ItemMaster createItem(ItemMaster item) {
        // Validation: Ensure Company ID is present
        if (item.getCompanyId() == null || item.getCompanyId().isEmpty()) {
            throw new RuntimeException("Company ID is mandatory");
        }

        // Validate Duplicate Code (Company Specific)
        if (itemRepository.existsByCompanyIdAndCode(item.getCompanyId(), item.getCode())) {
            throw new RuntimeException("Item Code " + item.getCode() + " already exists in this company!");
        }

        item.setActive(true);
        item.setCurrentStock(0.0);
        return itemRepository.save(item);
    }

    @Override
    public ItemMaster updateItem(String id, String companyId, ItemMaster item) {
        // Secure Fetch: Ensure user owns this item
        ItemMaster existing = itemRepository.findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new RuntimeException("Item not found or access denied"));

        existing.setName(item.getName());
        existing.setCategoryId(item.getCategoryId());
        existing.setBrandId(item.getBrandId());
        existing.setSalePrice(item.getSalePrice());
        existing.setMrp(item.getMrp());
        existing.setGstClassificationId(item.getGstClassificationId());
        existing.setSalesGlAccountId(item.getSalesGlAccountId());
        existing.setPurchaseGlAccountId(item.getPurchaseGlAccountId());
        // ... map other fields ...

        return itemRepository.save(existing);
    }

    @Override
    public ItemMaster getItemById(String id, String companyId) {
        return itemRepository.findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public Page<ItemMaster> getAllItems(String companyId, Pageable pageable) {
        return itemRepository.findByCompanyIdAndActiveTrue(companyId, pageable);
    }

    @Override
    public Page<ItemMaster> searchItems(String companyId, String keyword, Pageable pageable) {
        return itemRepository.searchByCompanyAndKeyword(companyId, keyword, pageable);
    }

    @Override
    public void deleteItem(String id, String companyId) {
        ItemMaster item = getItemById(id, companyId);
        item.setActive(false);
        itemRepository.save(item);
    }
}