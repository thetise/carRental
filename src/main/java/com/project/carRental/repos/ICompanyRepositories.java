package com.project.carRental.repos;

import com.project.carRental.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepositories extends JpaRepository<Company, Long> {
    //Company findByCompanyName(Company companyName);
}