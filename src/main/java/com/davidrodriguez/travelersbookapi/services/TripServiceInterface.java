package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.models.Member;
import com.davidrodriguez.travelersbookapi.models.NewTripStructure;
import com.davidrodriguez.travelersbookapi.models.Trip;

import java.time.LocalDate;
import java.util.List;

public interface TripServiceInterface {
    List<Trip> getAllTrips();
    List<Trip> getOwnTrips(String ownerId);
    Trip getTripById(String id);
    Trip getTrip(String id);
    String saveTrip(
            NewTripStructure trip
    );
    void deleteTrip(String id);
    Trip updateTrip(Trip trip);
}
