package com.nazmul.trip.controller;

import com.nazmul.trip.model.Trip;
import com.nazmul.trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TripAPIController {
    @Autowired
    private TripService tripService;

    @GetMapping("/trips")
    public ResponseEntity<Trip> getTripList(long id) {
        Trip Trip = tripService.getTrip(id);
        return new ResponseEntity<>(Trip, HttpStatus.OK);
    }

    @PostMapping("/create-trip")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip tripData) {
        Trip Trip = tripService.createTrip(tripData);
        return new ResponseEntity<>(Trip, HttpStatus.OK);
    }

    @PutMapping("/update-trip-status")
    public ResponseEntity<Trip> updateTripStatus(@RequestBody Trip tripData) {
        Trip Trip = tripService.updateTripStatus(tripData);
        return new ResponseEntity<>(Trip, HttpStatus.OK);
    }

    @PutMapping("/assign-transporter")
    public ResponseEntity<Trip> assignTransporter(@RequestBody Trip tripData) {
        Trip Trip = tripService.assignTransporter(tripData);
        return new ResponseEntity<>(Trip, HttpStatus.OK);
    }

    @GetMapping("/delete-trip")
    public ResponseEntity<String> updateTrip(long tripId) {
        tripService.deleteTrip(tripId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
