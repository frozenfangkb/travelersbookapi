package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.models.Member;
import com.davidrodriguez.travelersbookapi.models.NewTripStructure;
import com.davidrodriguez.travelersbookapi.models.Trip;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    void uploadTripImage(MultipartFile image, String tripId) throws IOException;
}
