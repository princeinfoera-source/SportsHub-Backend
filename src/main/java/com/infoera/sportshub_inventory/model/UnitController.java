package com.infoera.sportshub_inventory.model;

import com.infoera.sportshub_inventory.enums.UQC;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document("unit_of_measurement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitController {

    @Id
    private String id;

    // Internal unit code (PCS, KG)
    private String code;

    // Display name (Pieces, Kilogram)
    private String name;

    // Decimal precision allowed
    private Integer decimalPlaces;

    // GST UQC (NOS, KGS, MTR, LTR)
    private UQC uqc;

    private Boolean active = true;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
