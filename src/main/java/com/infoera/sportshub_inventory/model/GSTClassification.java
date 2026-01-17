package com.infoera.sportshub_inventory.model;

import com.infoera.sportshub_inventory.enums.GSTType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Document("gst_classification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GSTClassification {

    @Id
    private String id;

    private GSTType type; // e.g., HSN, SAC, or ItemCategory

    private String code;     // Internal code
    private String hsnCode;  // Official Government HSN/SAC code

    private String description;

    // Use BigDecimal for tax rates to avoid precision issues
    private BigDecimal gstRate;
    private BigDecimal cgst;
    private BigDecimal sgst;
    private BigDecimal igst;

    private Boolean active = true;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
