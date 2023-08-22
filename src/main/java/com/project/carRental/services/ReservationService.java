package com.project.carRental.services;

import com.project.carRental.entities.Car;
import com.project.carRental.entities.Company;
import com.project.carRental.entities.Reservation;
import com.project.carRental.entities.User;
import com.project.carRental.repos.ICarRepositories;
import com.project.carRental.repos.IReservationRepositories;
import com.project.carRental.requests.ReservationCreateRequest;
import com.project.carRental.responses.ReservationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    CompanyService companyService;

    CarService carService;
    ICarRepositories iCarRepositories;
    IReservationRepositories iReservationRepositories;
    UserService userService;

    public ReservationService(CompanyService companyService, CarService carService, ICarRepositories iCarRepositories, IReservationRepositories iReservationRepositories, UserService userService) {
        this.companyService = companyService;
        this.carService = carService;
        this.iCarRepositories = iCarRepositories;
        this.iReservationRepositories = iReservationRepositories;
        this.userService = userService;
    }

    public List<ReservationResponse> getAllReservation(Optional<Long> companyId) {
        List<Reservation> list;
        if(companyId.isPresent()){
            Company company = companyService.getOneCompany(companyId.get());
            list = iReservationRepositories.findByCompanyId(company);
        }
        else {
            list = iReservationRepositories.findAll();
        }
        return list.stream().map(p -> new ReservationResponse(p)).collect(Collectors.toList());
    }


    public Reservation createOneReservation(ReservationCreateRequest newReservationRequest) {
        Car car = carService.getOneCar(newReservationRequest.getCarId());


        Company company = companyService.getOneCompany(newReservationRequest.getCompanyId());
        User user = userService.getOneUser(newReservationRequest.getUserId());

        if(car == null || company == null || user == null){
            return null;
        }
        Reservation toSave = new Reservation();
        toSave.setId(newReservationRequest.getId());
        toSave.setStartDate(newReservationRequest.getStartDate());
        toSave.setFinishDate(newReservationRequest.getFinishDate());

        // AŞAĞIDAKİ 4 SATIR KOD PARÇACIĞINI EN AŞAĞIDA METOT ŞEKLİNDE YAZABİLİRDİM BURAYI KALABALIK ETMEMEK AÇISINDAN!!!
        LocalDate startDate = newReservationRequest.getStartDate();
        LocalDate finishDate = newReservationRequest.getFinishDate();
        long differenceInDays = ChronoUnit.DAYS.between(startDate, finishDate);
        long selectedCarPricePerDay = car.getPrice();

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d,M,yyyy");
        //LocalDate date = LocalDate.parse(startDate, formatter);

        toSave.setTotalPrice(differenceInDays * selectedCarPricePerDay);
        toSave.setCar(car);
        toSave.setCompany(company);
        toSave.setUser(user);
        return iReservationRepositories.save(toSave);
    }

    public Reservation getOneReservation(Long reservationId) {
        return iReservationRepositories.findById(reservationId).orElse(null);
    }

    public void deleteOneReservation(Long reservationId) {

        Reservation reservation = getOneReservation(reservationId);

        LocalDate currentDateTime = LocalDate.now();
        LocalDate reservationStartDate = reservation.getStartDate();

        long differenceInDays = ChronoUnit.DAYS.between(currentDateTime, reservationStartDate);

        if (differenceInDays >= 1) {
            iReservationRepositories.deleteById(reservationId);
            System.out.println("Reservation cancelled. Difference: " + differenceInDays + " days");
        } else {
            System.out.println("Reservation confirmed. Difference: " + differenceInDays + " days");
        }
    }
}
