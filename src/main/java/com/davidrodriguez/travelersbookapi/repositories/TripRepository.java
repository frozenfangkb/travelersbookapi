package com.davidrodriguez.travelersbookapi.repositories;

import com.davidrodriguez.travelersbookapi.models.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TripRepository extends MongoRepository<Trip, String> {
    List<Trip> findTripsByOwnerId(String id);
    Trip findTripById(String id);
}
