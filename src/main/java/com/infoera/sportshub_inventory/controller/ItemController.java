package com.infoera.sportshub_inventory.controller;

import com.infoera.sportshub_inventory.dto.ApiResponse;
import com.infoera.sportshub_inventory.model.ItemMaster;
import com.infoera.sportshub_inventory.security.JwtService;
import com.infoera.sportshub_inventory.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final JwtService jwtService;

    // --- Private Helper ---
    private String validateAndGetCompanyId(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or Invalid Authorization Header");
        }
        String token = authHeader.substring(7);
        if (!jwtService.isTokenValid(token)) {
            throw new RuntimeException("Session Expired or Invalid Token");
        }
        return jwtService.extractCompanyId(token);
    }

    // 1. Create Item
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createItem(
            @RequestHeader("Authorization") String token,
            @RequestBody ItemMaster item) {
        try {
            String companyId = validateAndGetCompanyId(token);
            item.setCompanyId(companyId);
            ItemMaster saved = itemService.createItem(item);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Item Created Successfully", saved));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // 2. Get All Items (Pagination Included)
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllItems(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            String companyId = validateAndGetCompanyId(token);
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
            Page<ItemMaster> items = itemService.getAllItems(companyId, pageable);
            return ResponseEntity.ok(new ApiResponse<>(true, "Items fetched", items));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // 3. Search Items
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchItems(
            @RequestHeader("Authorization") String token,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            String companyId = validateAndGetCompanyId(token);
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(new ApiResponse<>(true, "Search results", itemService.searchItems(companyId, keyword, pageable)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // 4. Update Item
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateItem(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody ItemMaster item) {
        try {
            String companyId = validateAndGetCompanyId(token);
            ItemMaster updated = itemService.updateItem(id, companyId, item);
            return ResponseEntity.ok(new ApiResponse<>(true, "Item updated successfully", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // 5. Delete Item (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteItem(
            @RequestHeader("Authorization") String token,
            @PathVariable String id) {
        try {
            String companyId = validateAndGetCompanyId(token);
            itemService.deleteItem(id, companyId);
            return ResponseEntity.ok(new ApiResponse<>(true, "Item deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}