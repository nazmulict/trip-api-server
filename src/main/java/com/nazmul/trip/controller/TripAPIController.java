package com.nazmul.trip.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final Logger logger = LoggerFactory.getLogger(TripAPIController.class);

    @Autowired
    private TripService tripService;

    @GetMapping("/trip")
    public ResponseEntity<Trip> getTrip(long id) throws Exception{
        logger.info("TripAPIController.getTrip START");
        Trip trip = tripService.getTrip(id);
        logger.info("TripAPIController.getTrip END");
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @GetMapping("/trips")
    public ResponseEntity<List<TripData>> getAllTripList() throws Exception{
        logger.info("TripAPIController.getAllTripList START");
        List<TripData> tripList;
        try {
            tripList = tripService.getAllTripList();
        }catch (Exception e){
            logger.error("TripAPIController.getAllTripList ERROR: ", e.getMessage());
            throw e;
        }
        logger.info("TripAPIController.getAllTripList END");
        return new ResponseEntity<>(tripList, HttpStatus.OK);
    }

    @PostMapping("/create-trip")
    public ResponseEntity<TripData> createTrip(@RequestBody TripData tripData) throws Exception{
        logger.info("TripAPIController.createTrip START");
        TripData tripObj;
        try {
             tripObj = tripService.createTrip(tripData);
        }catch (Exception e){
            logger.error("TripAPIController.createTrip ERROR: "+e.getMessage());
            throw e;
        }
        logger.info("TripAPIController.createTrip END");
        return new ResponseEntity<>(tripObj, HttpStatus.OK);
    }

    @PutMapping("/update-trip-status")
    public ResponseEntity<TripData> updateTripStatus(@RequestBody TripData tripData) throws Exception{
        logger.info("TripAPIController.updateTripStatus START");
        TripData tripObj;
        try{
            tripObj = tripService.updateTripStatus(tripData);
        }catch (Exception e){
            logger.error("TripAPIController.updateTripStatus ERROR: "+e.getMessage());
            throw e;
        }
        logger.info("TripAPIController.updateTripStatus END");
        return new ResponseEntity<>(tripObj, HttpStatus.OK);
    }

    @PutMapping("/assign-transporter")
    public ResponseEntity<TripData> assignTransporter(@RequestBody TripData tripData) throws Exception{
        logger.info("TripAPIController.assignTransporter START");
        TripData tripObj;
        try{
            tripObj = tripService.assignTransporter(tripData);
        }catch (Exception e){
            logger.error("TripAPIController.assignTransporter ERROR: "+e.getMessage());
            throw e;
        }
        logger.info("TripAPIController.assignTransporter END");
        return new ResponseEntity<>(tripObj, HttpStatus.OK);
    }

    @DeleteMapping("/delete-trip")
    public ResponseEntity<String> deleteTrip(long tripId) throws Exception{
        logger.info("TripAPIController.deleteTrip START");
        tripService.deleteTrip(tripId);
        logger.info("TripAPIController.deleteTrip END");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
