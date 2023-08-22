package com.project.carRental.repos;

import com.project.carRental.entities.Car;
import com.project.carRental.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICarRepositories extends JpaRepository<Car, Long> {
    List<Car> findByCompanyId(Company companyId);

    //Car findByCarName(String name);

    //Car findByCarName(String carName);
}
