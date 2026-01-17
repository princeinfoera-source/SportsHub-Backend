package com.infoera.sportshub_inventory.dto;

import com.infoera.sportshub_inventory.enums.CompanyType;
import com.infoera.sportshub_inventory.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private String companyName;
    private CompanyType businessType;
    private Country country;
    private String coaType;
}
