package com.project.carRental.requests;

import lombok.Data;

@Data
public class CarCreateRequest {
    Long id;
    String name;
    String brand;
    String fuelType;
    String gearType;
    String km;
    Long deposit;
    Long price;
    Long companyId;
}
