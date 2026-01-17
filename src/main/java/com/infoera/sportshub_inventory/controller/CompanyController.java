package com.infoera.sportshub_inventory.controller;

import com.infoera.sportshub_inventory.dto.CompanyDTO;
import com.infoera.sportshub_inventory.model.Company;
import com.infoera.sportshub_inventory.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> createCompany(@Valid @RequestBody CompanyDTO dto) {
        return companyService.createCompany(dto);
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAll();
    }
}
