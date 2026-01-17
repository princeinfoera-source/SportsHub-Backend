package com.infoera.sportshub_inventory.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("item_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
// COMPOUND INDEX: Ensures 'code' is unique ONLY within the same company.
// (Two different companies can both have an item with code "001")
@CompoundIndex(name = "company_item_code_idx", def = "{'companyId': 1, 'code': 1}", unique = true)
public class ItemMaster {

    @Id
    private String id;

    // --- TENANCY (CRITICAL) ---
    @Indexed
    private String companyId; // The ID of the Company who owns this data

    // Note: Usually Items are Global to a Company.
    // Stock is specific to a Branch/Warehouse (handled in Inventory Table).

    // ==========================================
    // 1. HEADER SECTION (Basic Info)
    // ==========================================
    private String itemMode;

    @Indexed
    private String name;

    private String code; // Removed @Indexed(unique=true) here, handled by CompoundIndex above

    private String underGroupId;
    private String stockUnitId;
    private String gstClassificationId;

    private String itemImagePath;

    // ==========================================
    // 2. TAB: ADVANCE INFO
    // ==========================================
    private String categoryId;
    private String brandId;
    private String itemType;
    private String unitOption;

    @Indexed
    private String barcode;
    private String autoBarcodePrefix;

    private Boolean subItemApplicable = false;
    private Boolean gstInputNotApplicable = false;
    private Boolean printBarcode = true;

    // ==========================================
    // 3. TAB: SALES CONFIG
    // ==========================================
    private String salesDescription;
    private String salesGlAccountId;

    private Double mrp = 0.0;
    private Double minSalePrice = 0.0;
    private Double salePrice = 0.0;
    private Double wholesaleRate = 0.0;
    private Double dealerRate = 0.0;

    private Double salesRateFactor = 0.0;
    private Double salesDiscount1 = 0.0;
    private Double salesDiscount2 = 0.0;

    // ==========================================
    // 4. TAB: PURCHASE CONFIG
    // ==========================================
    private String purchaseDescription;
    private String purchaseGlAccountId;

    private Double purchasePrice = 0.0;
    private Double purchaseRateFactor = 0.0;
    private Double purchaseDiscount1 = 0.0;
    private Double purchaseDiscount2 = 0.0;

    // ==========================================
    // 5. TAB: ATTRIBUTES CONFIG
    // ==========================================
    private String itemWorkflow;
    private String procurementType;

    private Double minStockLevel = 0.0;
    private Double maxStockLevel = 0.0;

    private String weighscaleMappingCode;
    private String rackBinNo;
    private String itemSetTemplateId;

    private Boolean batchWiseInventory = false;
    private Boolean batchWiseRate = false;

    private String drugType;
    private String salt;

    private Boolean skipLoyaltyProgram = false;
    private Boolean excludeInCvssApp = false;

    // ==========================================
    // 6. SUGGESTED ITEMS
    // ==========================================
    private List<String> suggestedItemIds = new ArrayList<>();

    // ==========================================
    // 7. ATTACHMENTS
    // ==========================================
    private List<String> attachmentUrls = new ArrayList<>();

    // ==========================================
    // SYSTEM FIELDS
    // ==========================================
    private Double currentStock = 0.0;
    private Boolean active = true;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}