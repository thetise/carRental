package com.project.carRental.services;

import com.project.carRental.entities.Car;
import com.project.carRental.entities.Company;
import com.project.carRental.repos.ICarRepositories;
import com.project.carRental.requests.CarCreateRequest;
import com.project.carRental.requests.CarUpdateRequest;
import com.project.carRental.responses.CarResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    ICarRepositories iCarRepositories;
    CompanyService companyService;

    public CarService(ICarRepositories iCarRepositories, CompanyService companyService) {
        this.iCarRepositories = iCarRepositories;
        this.companyService = companyService;
    }

    public List<CarResponse> getAllCars(Optional<Long> companyId) {
        List<Car> list;
        if(companyId.isPresent()){
            Company company = companyService.getOneCompany(companyId.get());
            list = iCarRepositories.findByCompanyId(company);
        }
        else{
            list = iCarRepositories.findAll();
        }
        return list.stream().map(p -> new CarResponse(p)).collect(Collectors.toList());
    }

    public Car createOneCar(CarCreateRequest newCarRequest) {
        Company company = companyService.getOneCompany(newCarRequest.getCompanyId());
        if(company == null)
            return null;
        Car toSave = new Car();
        toSave.setId(newCarRequest.getId());
        toSave.setName(newCarRequest.getName());
        toSave.setBrand(newCarRequest.getBrand());
        toSave.setFuelType(newCarRequest.getFuelType());
        toSave.setGearType(newCarRequest.getGearType());
        toSave.setKm(newCarRequest.getKm());
        toSave.setDeposit(newCarRequest.getDeposit());
        toSave.setPrice(newCarRequest.getPrice());
        toSave.setCompany(company);

        return iCarRepositories.save(toSave);
    }

    public Car getOneCar(Long carId) {
        return iCarRepositories.findById(carId).orElse(null);
    }

    //////////// BURADA ARABALARI ADI İLE ÇAĞIRMAK İÇİN BU SERVİSİ YAZDIM
    //public Car getOneCarByName(String carName){
    //    return iCarRepositories.findByCarName(carName);
    //}
    ////////////


    public Car updateOneCar(Long carId, CarUpdateRequest updateCarRequest) {
        Optional<Car> car = iCarRepositories.findById(carId);
        if(car.isPresent()){
            Car toUpdate = car.get();
            toUpdate.setPrice(updateCarRequest.getPrice());
            iCarRepositories.save(toUpdate);
            return toUpdate;
        }
        return null;
    }


    public void deleteOneCar(Long carId) {
        iCarRepositories.deleteById(carId);
    }

}
