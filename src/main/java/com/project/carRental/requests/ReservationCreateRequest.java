package com.project.carRental.requests;

import com.project.carRental.entities.Car;
import com.project.carRental.entities.Company;
import com.project.carRental.entities.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationCreateRequest {
    Long id;

    LocalDate startDate;
    LocalDate finishDate;

    Long totalPrice; // HATA VAR GİBİ BURDAA

    Long carId;
    Long companyId;
    Long userId;

    //Company companyName;
    //User userName;
}
