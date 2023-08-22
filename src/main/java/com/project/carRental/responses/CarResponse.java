package com.project.carRental.responses;

import com.project.carRental.entities.Car;
import lombok.Data;

@Data
public class CarResponse {
    Long id;

    String name;
    String brand;
    String fuelType;
    String gearType;
    String km;
    Long deposit;
    Long price;

    String companyName;
    Long companyId;

    public CarResponse(Car entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.brand = entity.getBrand();
        this.fuelType = entity.getFuelType();
        this.gearType = entity.getGearType();
        this.km = entity.getKm();
        this.deposit = entity.getDeposit();
        this.price = entity.getPrice();

        this.companyName = entity.getCompany().getUserName();
        this.companyId = entity.getCompany().getId();
    }
}
