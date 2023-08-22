package com.project.carRental.responses;

import com.project.carRental.entities.Reservation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationResponse {

    Long id;

    LocalDate startDate;
    LocalDate finishDate;

    Long totalPrice;

    String carName;
    Long carId;

    String userName;
    Long userId;

    String companyName;
    Long companyId;

    public ReservationResponse(Reservation entity) {
        this.id = entity.getId();
        this.startDate = entity.getStartDate();
        this.finishDate = entity.getFinishDate();
        this.totalPrice = entity.getTotalPrice();

        this.carName = entity.getCar().getName();
        this.carId = entity.getCar().getId();

        this.userName = entity.getUser().getUserName();
        this.userId = entity.getUser().getId();

        this.companyName = entity.getCompany().getUserName();
        this.companyId = entity.getCompany().getId();
    }
}
