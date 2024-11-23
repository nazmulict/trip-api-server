package com.nazmul.trip.model;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class TripData {
    private long tripId;
    private String createDate;
    private String pickupLocation;
    private String dropLocation;
    private String status;
    private String assignTransporter;
    private String realTimeLocation;
    private String otherDetails;

}
