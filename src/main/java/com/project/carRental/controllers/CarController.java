package com.project.carRental.controllers;

import com.project.carRental.entities.Car;
import com.project.carRental.requests.CarCreateRequest;
import com.project.carRental.requests.CarUpdateRequest;
import com.project.carRental.responses.CarResponse;
import com.project.carRental.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getAllCars(@RequestParam Optional<Long> companyId){
        return carService.getAllCars(companyId);
    }

    @PostMapping
    public Car createOneCar(@RequestBody CarCreateRequest newCarRequest){
        return carService.createOneCar(newCarRequest);
    }

    @GetMapping("/{carId}")
    public Car getOnecar(@PathVariable Long carId){
        return carService.getOneCar(carId);
    }

    @PutMapping("/{carId}")
    public Car updateOneCar(@PathVariable Long carId, @RequestBody CarUpdateRequest updateCarRequest){
        return carService.updateOneCar(carId, updateCarRequest);
    }

    @DeleteMapping("/{carId}")
    public void deleteOneCar(@PathVariable Long carId){
        carService.deleteOneCar(carId);
    }
}
