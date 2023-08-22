package com.project.carRental.controllers;

import com.project.carRental.entities.Reservation;
import com.project.carRental.requests.ReservationCreateRequest;
import com.project.carRental.responses.ReservationResponse;
import com.project.carRental.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponse> getAllReservations(@RequestParam Optional<Long> companyId){
        return reservationService.getAllReservation(companyId);
    }

    @PostMapping
    public Reservation createOneReservation(@RequestBody ReservationCreateRequest newReservationRequest){
        return reservationService.createOneReservation(newReservationRequest);
    }

    @GetMapping("/{reservationId}")
    public Reservation getOneReservation(@PathVariable Long reservationId){
        return reservationService.getOneReservation(reservationId);
    }

    @DeleteMapping("/{reservationId}")
    public void deleteOneReservation(@PathVariable Long reservationId){
        reservationService.deleteOneReservation(reservationId);
    }
}
