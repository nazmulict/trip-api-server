package com.nazmul.trip.repository;

import com.nazmul.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip findTripById(long id);

    List<Trip> findAllByStatus(String name);
}
