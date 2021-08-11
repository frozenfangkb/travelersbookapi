package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.models.Member;
import com.davidrodriguez.travelersbookapi.models.Trip;

import java.time.LocalDate;
import java.util.List;

public interface TripServiceInterface {
    List<Trip> getAllTrips();
    List<Trip> getOwnTrips(String ownerId);
    Trip getTrip(String id);
    String saveTrip(
            String name,
            String destination,
            String lodgingAddress,
            LocalDate initialDate,
            LocalDate endDate,
            List<String> members,
            String mainImage
    );
    void deleteTrip(String id);
    Trip updateTrip(Trip trip);
}
