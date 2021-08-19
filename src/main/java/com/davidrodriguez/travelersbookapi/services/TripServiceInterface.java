package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.models.NewTripStructure;
import com.davidrodriguez.travelersbookapi.models.Trip;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TripServiceInterface {
    List<Trip> getAllTrips();
    List<Trip> getOwnTrips(String ownerId);
    Trip getTripById(String id);
    Trip getTrip(String id);
    String saveTrip(
            NewTripStructure trip,
            String userId
    );
    void deleteTrip(String id);
    Trip updateTrip(Trip trip);
    void uploadTripImage(MultipartFile image, String tripId) throws IOException;
    ClassPathResource getTripImage(String tripId) throws IOException;
    boolean updateTripSmallDescription(String tripId, String description);
}
