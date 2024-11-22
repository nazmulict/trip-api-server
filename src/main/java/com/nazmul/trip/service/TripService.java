package com.nazmul.trip.service;

import com.nazmul.trip.model.Trip;
import com.nazmul.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip createTrip(Trip tripData){
        return tripRepository.save(tripData);
    }

    public Trip getTrip(long tripId){
        return tripRepository.findTripById(tripId);
    }

    public Trip updateTripStatus(Trip tripData){
        Trip updateTrip = tripRepository.findTripById(tripData.getId());
        updateTrip.setStatus(tripData.getStatus());
        return tripRepository.save(updateTrip);
    }

    public Trip assignTransporter(Trip tripData){
        Trip updateTrip = tripRepository.findTripById(tripData.getId());
        updateTrip.setStatus(tripData.getStatus());
        updateTrip.setAssignTransporter(tripData.getAssignTransporter());
        return tripRepository.save(updateTrip);
    }

    public void deleteTrip(long TripId){
        Trip deleteTrip = tripRepository.findTripById(TripId);
        tripRepository.delete(deleteTrip);
    }
}
