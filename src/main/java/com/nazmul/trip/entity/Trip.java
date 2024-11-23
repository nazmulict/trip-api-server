package com.nazmul.trip.entity;

import com.nazmul.trip.entity.common.BaseEntity;
import com.nazmul.trip.entity.enumeration.StatusType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="trip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class Trip extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="pickup_location", nullable = false)
    private String pickupLocation;

    @Column(name="drop_location", nullable = false)
    private String dropLocation;

    @Enumerated(EnumType.STRING)
    @Column(name="status", length=100, nullable = false)
    private StatusType status;

    @Column(name="assign_transporter")
    private String assignTransporter;

    @Column(name="real_time_location")
    private String realTimeLocation;

    @Column(name="other_details")
    private String otherDetails;

}
