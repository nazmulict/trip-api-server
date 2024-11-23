package com.nazmul.trip.service;

import com.nazmul.trip.entity.enumeration.StatusType;
import com.nazmul.trip.entity.Trip;
import com.nazmul.trip.model.TripData;
import com.nazmul.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public TripData createTrip(TripData tripData){
        // parse date from model data
        String createDateStr = tripData.getCreateDate();
        Date createDate = new Date();
        try {
            SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
            createDate = outFormat.parse(createDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //set the trip model data
        Trip tripObj = Trip.builder()
                .pickupLocation(tripData.getPickupLocation())
                .dropLocation(tripData.getDropLocation())
                .status(StatusType.UNASSIGNED)
                .assignTransporter(tripData.getAssignTransporter())
                .realTimeLocation(tripData.getRealTimeLocation())
                .otherDetails(tripData.getOtherDetails())
                .build();
        tripObj.setActive(true);
        tripObj.setCreatedAt(createDate);
        tripObj.setUpdatedAt(new Date());
        tripRepository.save(tripObj);
        return getBuildTripData(tripObj);
    }

    public List<TripData> getAllTripList(){
        List<Trip> tripList = tripRepository.findAll();
        List<TripData> tripDataList = new ArrayList<>();
        for(Trip trip : tripList){
            TripData tripData = getBuildTripData(trip);
            tripDataList.add(tripData);
        }
        return tripDataList;
    }

    public Trip getTrip(long tripId){
        return tripRepository.findTripById(tripId);
    }

    public TripData updateTripStatus(TripData tripData){
        Trip updateTrip = tripRepository.findTripById(tripData.getTripId());
        if(tripData.getStatus().equalsIgnoreCase(StatusType.RUNNING.values().toString())){
            updateTrip.setStatus(StatusType.RUNNING);
        }
        if (tripData.getStatus().equalsIgnoreCase(StatusType.COMPLETED.values().toString())) {
            updateTrip.setStatus(StatusType.COMPLETED);
        }
        if (tripData.getStatus().equalsIgnoreCase(StatusType.CANCELLED.values().toString())) {
            updateTrip.setStatus(StatusType.CANCELLED);
        }
        tripRepository.save(updateTrip);
        return getBuildTripData(updateTrip);
    }

    public TripData assignTransporter(TripData tripData){
        Trip updateTrip = tripRepository.findTripById(tripData.getTripId());
        updateTrip.setStatus(StatusType.BOOKED);
        updateTrip.setAssignTransporter(tripData.getAssignTransporter());
        tripRepository.save(updateTrip);
        return getBuildTripData(updateTrip);
    }

    private static TripData getBuildTripData(Trip updateTrip) {
        return TripData.builder()
                .tripId(updateTrip.getId())
                .createDate(updateTrip.getCreatedAt().toString())
                .pickupLocation(updateTrip.getPickupLocation())
                .dropLocation(updateTrip.getDropLocation())
                .status(updateTrip.getStatus().toString())
                .assignTransporter(updateTrip.getAssignTransporter())
                .realTimeLocation(updateTrip.getRealTimeLocation())
                .otherDetails(updateTrip.getOtherDetails())
                .build();
    }

    public void deleteTrip(long TripId){
        Trip deleteTrip = tripRepository.findTripById(TripId);
        tripRepository.delete(deleteTrip);
    }
}
