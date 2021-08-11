package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.models.Member;
import com.davidrodriguez.travelersbookapi.models.Trip;
import com.davidrodriguez.travelersbookapi.repositories.TripRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service @Data
public class TripService implements TripServiceInterface {
    private final TripRepository tripRepository;

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll(); // TODO: Paginate this response
    }

    @Override
    public List<Trip> getOwnTrips(String ownerId) {
        return tripRepository.findTripsByOwnerId(ownerId);
    }

    @Override
    public Trip getTrip(String id) {
        return tripRepository.findTripById(id);
    }

    @Override
    public String saveTrip(String name, String destination, String lodgingAddress, LocalDate initialDate, LocalDate endDate, List<Member> members, String mainImage) {
        return null;
    }

    @Override
    public void deleteTrip(String id) {
        // TODO: Check for user ownership of the trip and authorization when jwt is implemented
        tripRepository.deleteById(id);
    }

    @Override
    public Trip updateTrip(Trip trip) {
        return null;
    }
}
