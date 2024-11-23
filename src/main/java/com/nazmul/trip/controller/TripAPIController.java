package com.nazmul.trip.controller;

import com.nazmul.trip.entity.Trip;
import com.nazmul.trip.model.TripData;
import com.nazmul.trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TripAPIController {
    @Autowired
    private TripService tripService;

    @GetMapping("/trip")
    public ResponseEntity<Trip> getTripList(long id) {
        Trip trip = tripService.getTrip(id);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @GetMapping("/trips")
    public ResponseEntity<List<TripData>> getAllTripList() {
        List<TripData> tripList = tripService.getAllTripList();
        return new ResponseEntity<>(tripList, HttpStatus.OK);
    }

    @PostMapping("/create-trip")
    public ResponseEntity<TripData> createTrip(@RequestBody TripData tripData) {
        TripData tripObj = tripService.createTrip(tripData);
        return new ResponseEntity<>(tripObj, HttpStatus.OK);
    }

    @PutMapping("/update-trip-status")
    public ResponseEntity<TripData> updateTripStatus(@RequestBody TripData tripData) {
        TripData tripObj = tripService.updateTripStatus(tripData);
        return new ResponseEntity<>(tripObj, HttpStatus.OK);
    }

    @PutMapping("/assign-transporter")
    public ResponseEntity<TripData> assignTransporter(@RequestBody TripData tripData) {
        TripData tripObj = tripService.assignTransporter(tripData);
        return new ResponseEntity<>(tripObj, HttpStatus.OK);
    }

    @GetMapping("/delete-trip")
    public ResponseEntity<String> updateTrip(long tripId) {
        tripService.deleteTrip(tripId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
