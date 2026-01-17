package com.infoera.sportshub_inventory.service.impl;

import com.infoera.sportshub_inventory.dto.CompanyDTO;
import com.infoera.sportshub_inventory.model.Company;
import com.infoera.sportshub_inventory.repository.CompanyRepository;
import com.infoera.sportshub_inventory.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public ResponseEntity<Company> createCompany(CompanyDTO dto) {

        Company company = new Company();
        company.setCompanyName(dto.getCompanyName());
        company.setBusinessType(dto.getBusinessType());
        company.setCountry(dto.getCountry());
        company.setCoaType(dto.getCoaType());
        company.setCreatedAt(new Date());
        company.setUpdatedAt(new Date());

        Company savedCompany = companyRepository.save(company);
        return ResponseEntity.ok(savedCompany);
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
