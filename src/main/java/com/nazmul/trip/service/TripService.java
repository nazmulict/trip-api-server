package com.nazmul.trip.service;

import com.nazmul.trip.common.CommonConstant;
import com.nazmul.trip.entity.Trip;
import com.nazmul.trip.entity.enumeration.StatusType;
import com.nazmul.trip.exception.DataNotFoundException;
import com.nazmul.trip.model.TripData;
import com.nazmul.trip.repository.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.xml.validation.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TripService {
    public static final Logger logger = LoggerFactory.getLogger(TripService.class);

    @Autowired
    private TripRepository tripRepository;

    public TripData createTrip(TripData tripData) throws Exception{
        logger.info("TripService.createTrip START");
        // parse date from model data
        if(tripData != null){
            String createDateStr = tripData.getCreateDate();
            Date createDate = new Date();
            try {
                SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
                createDate = outFormat.parse(createDateStr);
            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage());
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
            logger.info("TripService.createTrip END");
            return getBuildTripData(tripObj);
        }else{
            logger.error("TripService.createTrip ERROR: "+CommonConstant.DATA_NOT_FOUND);
            throw new DataNotFoundException(CommonConstant.DATA_NOT_FOUND);
        }

    }

    public List<TripData> getAllTripList() throws Exception{
        List<Trip> tripList = tripRepository.findAll();
        List<TripData> tripDataList = new ArrayList<>();
        for(Trip trip : tripList){
            TripData tripData = getBuildTripData(trip);
            tripDataList.add(tripData);
        }
        return tripDataList;
    }

    public TripData getTrip(long tripId){
        Trip tripObj = tripRepository.findTripById(tripId);
        return getBuildTripData(tripObj);
    }

    public TripData updateTripStatus(TripData tripData) throws Exception{
        logger.info("TripService.updateTripStatus START");
        if(tripData != null && !ObjectUtils.isEmpty(tripData)){
            Trip updateTrip = tripRepository.findTripById(tripData.getTripId());
            if(updateTrip != null){
                if(tripData.getStatus().equalsIgnoreCase(StatusType.RUNNING.name())){
                    updateTrip.setStatus(StatusType.RUNNING);
                }
                if (tripData.getStatus().equalsIgnoreCase(StatusType.COMPLETED.name())) {
                    updateTrip.setStatus(StatusType.COMPLETED);
                }
                if (tripData.getStatus().equalsIgnoreCase(StatusType.CANCELLED.name())) {
                    updateTrip.setStatus(StatusType.CANCELLED);
                }
                tripRepository.save(updateTrip);
                logger.info("TripService.updateTripStatus END");
                return getBuildTripData(updateTrip);
            }else{
                throw new DataNotFoundException(CommonConstant.INVALID_PARAMETER);
            }

        }else{
            logger.error("TripService.updateTripStatus ERROR: "+CommonConstant.OBJECT_DATA_NOT_FOUND);
            throw new DataNotFoundException(CommonConstant.OBJECT_DATA_NOT_FOUND);
        }
    }

    public TripData assignTransporter(TripData tripData) throws Exception{
        logger.info("TripService.assignTransporter START");
        if(tripData != null && !ObjectUtils.isEmpty(tripData)){
            Trip updateTrip = tripRepository.findTripById(tripData.getTripId());
            if(updateTrip != null){
                // assign transporter and status booked set
                updateTrip.setStatus(StatusType.BOOKED);
                updateTrip.setAssignTransporter(tripData.getAssignTransporter());
                tripRepository.save(updateTrip);
                logger.info("TripService.assignTransporter END");
                return getBuildTripData(updateTrip);
            }else{
                throw new DataNotFoundException(CommonConstant.INVALID_PARAMETER);
            }

        }else{
            logger.error("TripService.assignTransporter ERROR: "+CommonConstant.OBJECT_DATA_NOT_FOUND);
            throw new DataNotFoundException(CommonConstant.OBJECT_DATA_NOT_FOUND);
        }

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
