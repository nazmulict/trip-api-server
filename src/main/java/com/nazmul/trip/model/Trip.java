package com.nazmul.trip.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="trip")
public class Trip {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="pickup_location")
    private String pickupLocation;

    @Column(name="drop_location")
    private String dropLocation;

    @Column(name="status", length=100)
    private String status;

    @Column(name="assign_transporter")
    private String assignTransporter;

    @Column(name="real_time_location")
    private String realTimeLocation;

    @Column(name="other_details")
    private String otherDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignTransporter() {
        return assignTransporter;
    }

    public void setAssignTransporter(String assignTransporter) {
        this.assignTransporter = assignTransporter;
    }

    public String getRealTimeLocation() {
        return realTimeLocation;
    }

    public void setRealTimeLocation(String realTimeLocation) {
        this.realTimeLocation = realTimeLocation;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }
}
