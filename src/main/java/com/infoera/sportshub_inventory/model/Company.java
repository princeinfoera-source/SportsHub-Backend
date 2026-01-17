package com.infoera.sportshub_inventory.model;

import com.infoera.sportshub_inventory.enums.CompanyType;
import com.infoera.sportshub_inventory.enums.Country;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    private String id;
    private String companyName;
    private CompanyType businessType;
    private Country country;
    private String coaType;
    private Date createdAt;
    private Date updatedAt;
}
