package com.project.carRental.controllers;

import com.project.carRental.entities.Company;
import com.project.carRental.services.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }

    @PostMapping
    public Company createOneCompany(@RequestBody Company newCompany){
        return companyService.createOneCompany(newCompany);
    }

    @GetMapping("/{companyId}")
    public Company getOneCompany(@PathVariable Long companyId){
        return companyService.getOneCompany(companyId);
    }

    @PutMapping("/{companyId}")
    public Company updateOneCompany(@PathVariable Long companyId, @RequestBody Company updateCompany){
        return companyService.updateOneCompany(companyId, updateCompany);
    }

    @DeleteMapping("/{companyId}")
    public void deleteOneCompany(@PathVariable Long companyId){
        companyService.deleteOneCompany(companyId);
    }
}
