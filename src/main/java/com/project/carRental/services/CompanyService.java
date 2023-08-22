package com.project.carRental.services;

import com.project.carRental.entities.Company;
import com.project.carRental.repos.ICompanyRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    ICompanyRepositories iCompanyRepositories;

    public CompanyService(ICompanyRepositories iCompanyRepositories) {
        this.iCompanyRepositories = iCompanyRepositories;
    }

    public List<Company> getAllCompany() {
        return iCompanyRepositories.findAll();
    }

    public Company createOneCompany(Company newCompany) {
        return iCompanyRepositories.save(newCompany);
    }

    public Company getOneCompany(Long companyId) {
        return iCompanyRepositories.findById(companyId).orElse(null);
    }

    /////// BURADA BUNU COMPANY NAME İLE ÇAĞIRMAK İÇİN BU SERVİSİ YAZDIM.
    //public Company getOneCompanyByName(Company companyName){
    //    return iCompanyRepositories.findByCompanyName(companyName);
    //}
    ///////

    public Company updateOneCompany(Long companyId, Company updateCompany) {
        Optional<Company> company = iCompanyRepositories.findById(companyId);
        if(company.isPresent()){
            Company foundCompany = company.get();

            if(updateCompany.getPassword() != null){
                foundCompany.setPassword(updateCompany.getPassword());
            }else{
                foundCompany.setPassword(company.get().getPassword());
            }

            if(updateCompany.getCity() != null){
                foundCompany.setCity(updateCompany.getCity());
            }else{
                foundCompany.setCity(company.get().getCity());
            }

            iCompanyRepositories.save(foundCompany);
            return foundCompany;
        }
        else{
            return null;
        }
    }

    public void deleteOneCompany(Long companyId) {
        iCompanyRepositories.deleteById(companyId);
    }
}