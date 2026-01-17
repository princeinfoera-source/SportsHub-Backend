package com.infoera.sportshub_inventory.service;

import com.infoera.sportshub_inventory.dto.CompanyDTO;
import com.infoera.sportshub_inventory.model.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {

    ResponseEntity<Company> createCompany(CompanyDTO dto);

    List<Company> getAll();
}