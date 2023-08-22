package com.project.carRental.repos;

import com.project.carRental.entities.Company;
import com.project.carRental.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservationRepositories extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCompanyId(Company companyId);
}
